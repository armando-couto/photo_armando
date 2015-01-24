package app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import app.models.Product;
import app.repositories.ProductRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.ByteStreams;

@Resource
public class ProductController {

	private final Result result;
	private final ProductRepository repository;
	
	private final Validator validator;
	
	public ProductController(Result result, ProductRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/products")
	public List<Product> index() {
		return repository.findAll();
	}
	
	@Post("/products")
	public void create(Product product, UploadedFile imageFile) throws IOException {
		validator.validate(product);
		validator.onErrorUsePageOf(this).newProduct();
		
		if (imageFile != null) {
			InputStream file = imageFile.getFile();
			product.setImage(ByteStreams.toByteArray(file));
			product.setContentType(imageFile.getContentType());
			product.setNome(imageFile.getFileName());
		}
		
		repository.create(product);
		result.redirectTo(this).index();
	}
	
	@Get("/products/new")
	public Product newProduct() {
		return new Product();
	}
	
	@Get("/products/{product.id}")
	public Product show(Product product) {
		return repository.find(product.getId());
	}

	@Delete("/products/{product.id}")
	public void destroy(Product product) {
		repository.destroy(repository.find(product.getId()));
		result.redirectTo(this).index();  
	}
}
