package br.com.toni.telacadasto.atividade04;

import br.com.toni.telacadasto.atividade04.controller.CadastroContatosApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TelaCadastroApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(TelaCadastroApplication.class).run(args);
		CadastroContatosApp.setSpringContext(context);
		CadastroContatosApp.launch(CadastroContatosApp.class, args);
	}

}
