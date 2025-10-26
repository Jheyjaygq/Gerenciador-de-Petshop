package com.example.CRUD.de.animais;

import com.example.CRUD.de.animais.Entity.AnimalEntity;
import com.example.CRUD.de.animais.Service.AnimalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CrudDeAnimaisApplication implements CommandLineRunner {

	private final AnimalService service;

	public CrudDeAnimaisApplication(AnimalService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudDeAnimaisApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n=== Gerenciamento do Pet Shop ===");
			System.out.println("1. Listar animais");
			System.out.println("2. Cadastrar animal");
			System.out.println("3. Buscar por nome");
			System.out.println("4. Deletar animal");
			System.out.println("5. Atualizar animal");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = limitarNumeros(scanner, 0, 5);

			if (opcao == 1){
				listarAnimais();
			} else if (opcao == 2) {
				cadastrarAnimal(scanner);
			} else if (opcao == 3) {
				buscarPorNome(scanner);
			} else if (opcao == 4) {
				deletarAnimal(scanner);
			} else if (opcao == 5) {
				atualizarAnimal(scanner);

			} else if (opcao == 0){
				System.out.println("Operações encerradas, Saindo...");
				break;

			}
			else {
				System.out.println("Digite um numéro valido!");
			}
		}
	}

	// esse metodo lista todos os animais e seus atributos
	private void listarAnimais() {
		List<AnimalEntity> animais = service.listar();
		if (animais.isEmpty()) {
			System.out.println("Nenhum animal cadastrado.");
			return;
		}
		System.out.println("\nAnimais cadastrados: ");
		animais.forEach(a ->
				System.out.println("Id:" + a.getId() +
						" | Nome: " + a.getNome() +
						" | Especie: " + a.getEspecie() +
						" | Raça: " + a.getRaca() +
						" | Idade:" + a.getIdade() +
						" | Data de Cadastro: " + a.getDataCadastro())
		);
	}
	// cadastra um animal
	private void cadastrarAnimal(Scanner scanner) {
		System.out.println("\nCadastro de Animal: ");

		System.out.print("Nome (obrigatório): ");
		String nome = lerStringNaoVazia(scanner);

		System.out.print("Espécie (obrigatório): ");
		String especie = lerStringNaoVazia(scanner);

		System.out.print("Raça (opcional - ENTER para pular): ");
		String raca = lerStringOpcional(scanner);

		System.out.print("Idade (0-100): ");
		int idade = limitarNumeros(scanner, 0, 100);

		AnimalEntity a = new AnimalEntity();
		a.setNome(nome);
		a.setEspecie(especie);
		a.setRaca(raca.isBlank() ? null : raca);
		a.setIdade(idade);

		if (a.getDataCadastro() == null) {
			a.setDataCadastro(LocalDate.now());
		}

		AnimalEntity salvo = service.salvar(a);
		System.out.println("Animal cadastrado! ID = " + salvo.getId());
	}

	//busca animal por nome fornecido
	private void buscarPorNome(Scanner scanner) {
		System.out.println("\nBuscar animal por nome");
		System.out.println("Digite o nome do animal que deseja buscar: ");
		String nome = lerStringNaoVazia(scanner);

		List<AnimalEntity> encontrados = service.buscarPorNome(nome);
		if (encontrados.isEmpty()) {
			System.out.println("Nenhum animal encontrado com o nome: " + nome);
			return;
		}

		System.out.println("Animais encontrados com o nome: " + nome);
		encontrados.forEach(a ->
				System.out.println("Id:" + a.getId() +
						" | Nome: " + a.getNome() +
						" | Especie: " + a.getEspecie() +
						" | Raça: " + a.getRaca() +
						" | Idade:" + a.getIdade() +
						" | Data de Cadastro: " + a.getDataCadastro())
		);
	}

	//deleta animal por id fornecido
	private void deletarAnimal(Scanner scanner) {
		System.out.println("\nDeletar animal");
		System.out.print("ID para deletar (digite 0 para cancelar): ");
		Long id = lerLong(scanner);
		if (id == 0L) {
			System.out.println("Cancelado.");
			return;
		}

		service.buscarPorId(id).ifPresentOrElse(entity -> {
			service.deletar(id);
			System.out.println("Animal com ID " + id + " deletado.");
		}, () -> System.out.println("ID não encontrado: " + id));
	}
	//atualiza animal por id fornecido
	private void atualizarAnimal(Scanner scanner) {
		System.out.println("\nAtualizar animal");
		System.out.print("Digite o ID do animal que deseja atualizar (ou 0 para cancelar): ");
		Long id = lerLong(scanner);

		if (id == 0L) {
			System.out.println("Operação cancelada.");
			return;
		}

		service.buscarPorId(id).ifPresentOrElse(animalExistente -> {
			System.out.println("Animal encontrado: ");
			System.out.println("Nome atual: " + animalExistente.getNome());
			System.out.print("Novo nome (ENTER para manter): ");
			String nome = scanner.nextLine().trim();
			if (!nome.isBlank()) animalExistente.setNome(nome);

			System.out.println("Espécie atual: " + animalExistente.getEspecie());
			System.out.print("Nova espécie (ENTER para manter): ");
			String especie = scanner.nextLine().trim();
			if (!especie.isBlank()) animalExistente.setEspecie(especie);

			System.out.println("Raça atual: " + safe(animalExistente.getRaca()));
			System.out.print("Nova raça (ENTER para manter): ");
			String raca = scanner.nextLine().trim();
			if (!raca.isBlank()) animalExistente.setRaca(raca);

			System.out.println("Idade atual: " + animalExistente.getIdade());
			System.out.print("Nova idade (ou ENTER para manter): ");
			Integer novaIdade = lerIdadeOpcional(scanner, 0, 100);
			if (novaIdade != null) {
				animalExistente.setIdade(novaIdade);
			}


			AnimalEntity atualizado = service.atualizar(id, animalExistente);

			System.out.println("Animal atualizado com sucesso!");
			System.out.println("ID: " + atualizado.getId() +
					" | Nome: " + atualizado.getNome() +
					" | Espécie: " + atualizado.getEspecie() +
					" | Raça: " + safe(atualizado.getRaca()) +
					" | Idade: " + atualizado.getIdade() +
					" | Data de Cadastro: " + atualizado.getDataCadastro());

		}, () -> System.out.println("Animal com ID " + id + " não encontrado."));
	}


	//le uma string e não o usuário fornecer um valor vazio
	private String lerStringNaoVazia(Scanner scanner) {
		String line;
		do {
			line = scanner.nextLine().trim();
			if (line.isEmpty()) {
				System.out.println("Este campo é obrigatório. Não deixe em branco.");
			}
		} while (line.isEmpty());
		return line;
	}

	private String lerStringOpcional(Scanner scanner) {
		return scanner.nextLine().trim();
	}

	//le uma string, tranforma em int e verifica se o valor está no intervalo permitido, caso o valor
	//lido não seja um número lança uma exceção
	private int limitarNumeros(Scanner scanner, int min, int max) {
		while (true) {
			try {
				String line = scanner.nextLine().trim();
				int val = Integer.parseInt(line);
				if (val < min || val > max) {
					System.out.print("Informe um número entre " + min + " e " + max + ": ");
					continue;
				}
				return val;
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida. Tente novamente: ");
			}
		}
	}


	//le uma string e tenta tranformar em long, verifica se é maior que zero, lança uma exceção
	// caso o valor fornecido não seja um long
	private Long lerLong(Scanner scanner) {
		while (true) {

			try {
				String line = scanner.nextLine().trim();
				long val = Long.parseLong(line);
				if (val < 0) {
					System.out.println("ID inválido. Informe um número >= 0.");
					continue;
				}
				return val;
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida: digite um número inteiro válido.");
			}
		}
	}

	private String safe(String s) {
		return s == null ? "-" : s;
	}

	//le uma string e caso esteja vazio retorna null, caso contrario tenta tranforma em int e verifica
	// se esta no intervalo fornecido, lança uma exceção caso o valor fornecido não seja um número
	private Integer lerIdadeOpcional(Scanner scanner, int min, int max) {
		String line = scanner.nextLine().trim();
		if (line.isEmpty()) {
			return null;
		}

		try {
			int idade = Integer.parseInt(line);
			if (idade < min || idade > max) {
				System.out.println("Idade inválida. Deve estar entre " + min + " e " + max + ". Mantendo a idade anterior.");
				return null;
			}
			return idade;
		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Digite apenas números. Mantendo a idade anterior.");
			return null;
		}
	}
}
