package guru.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	private void initData() {

		// eric
		Author eric = new Author("Eric", "Evans");
		Publisher harper = new Publisher("Harper Collins", "123 main st");
		Book ddd = new Book("Domain Driven Design", "1234", harper);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		authorRepository.save(eric);
		publisherRepository.save(harper);
		bookRepository.save(ddd);
		// rod
		Author rod = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "spring st");
		Book noEJB = new Book("J2EE development without EJB", "23444", worx);
		eric.getBooks().add(noEJB);
		ddd.getAuthors().add(rod);
		authorRepository.save(rod);
		publisherRepository.save(worx);
		bookRepository.save(noEJB);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}
}
