import java.io.*;
import java.util.*;

public class Computador {

	String marcasComp[] = {"Dell", "Lenovo", "HP", "Positivo", "Asus", "Apple", "IBM"}; 
	String processadoresComp[] = {"Intel Core i3", "Intel Core i5", "Intel Core i7", "Intel Core i9", "AMD Ryzen" , "AMD Athlon"};
	int tamanhoTelas[] = {10, 12, 15, 20, 25, 28};
	
	char 	ativo;
	String	marca;
	String 	codComp;
	String 	modelo;
	String 	processador;
	int quantMemoria;
	int tamanhoTela;
	int quantEstoque;
	float preco;
	int quantVendida;
	String dtUltimaVenda; // <-- Data da última Venda
	
	Scanner leia = new Scanner(System.in);

	public long pesquisarComputador (String codCompPesq) {	
		// metodo para localizar um registro no arquivo em disco
		long posicaoCursorArquivo = 0;
		try { 
			RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
			while (true) {
				posicaoCursorArquivo  = arqComp.getFilePointer();	// posicao do inicio do registro no arquivo
				ativo		  = arqComp.readChar();
				marca  		  = arqComp.readUTF();
				codComp		  = arqComp.readUTF();
				modelo        = arqComp.readUTF();
				processador   = arqComp.readUTF();
				quantMemoria  = arqComp.readInt();
				tamanhoTela   = arqComp.readInt();
				quantEstoque  = arqComp.readInt();
				preco		  = arqComp.readFloat();
				quantVendida  = arqComp.readInt();
				dtUltimaVenda = arqComp.readUTF();
				

				if ( codCompPesq.equals(codComp) && ativo == 'S') {
					arqComp.close();
					return posicaoCursorArquivo;
				}
			}
		}catch (EOFException e) {
			return -1; // registro nao foi encontrado
		}catch (IOException e) { 
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
			return -1;
		}
	}
	
	public boolean checarMarca() {
		
		for(int x = 0; x < marcasComp.length; x++) {
			if(marca.equals(marcasComp[x])) {
				return true;
			}
		}
		return false;
	}

	public boolean checarProcessadores() {
		
		for(int x = 0; x < processadoresComp.length; x++) {
			if(processador.equals(processadoresComp[x])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checarTelas() {
		
		for(int x = 0; x < tamanhoTelas.length; x++) {
			if(processador.equals(tamanhoTelas[x])) {
				return true;
			}
		}
		return false;
	}
	
	public void salvarComputador() {	
		// metodo para incluir um novo registro no final do arquivo em disco
		try {
			RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");	
			arqComp.seek(arqComp.length());  // posiciona o ponteiro no final do arquivo (EOF)
			arqComp.writeChar(ativo);
			arqComp.writeUTF(marca);
			arqComp.writeUTF(codComp);
			arqComp.writeUTF(modelo);
			arqComp.writeUTF(processador);
			arqComp.writeInt(quantMemoria);
			arqComp.writeInt(tamanhoTela);
			arqComp.writeInt(quantEstoque);
			arqComp.writeFloat(preco);
			arqComp.writeInt(quantVendida);
			arqComp.writeUTF(dtUltimaVenda);
			arqComp.close();
			
			System.out.println("Dados gravados com sucesso !\n");
		}catch (IOException e) { 
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
		}
	}

	public void desativarComputador(long posicao)	{    
		// metodo para alterar o valor do campo ATIVO para N, tornando assim o registro excluido
		try {
			RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");			
			arqComp.seek(posicao);
			arqComp.writeChar('N');   // desativar o registro antigo
			arqComp.close();
		}catch (IOException e) { 
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
		}
	}
	
	// ***********************   Incluir     *****************************
	
	public void Incluir() {
		
		int estoqueAtual;
		
		
		System.out.println("Digite a marca que deseja incluir estoque: ");
		String novaMarca = leia.nextLine();
		
		System.out.print("Digite a quantidade a incluir: ");
		int novoEstoque = leia.nextInt();
		
		
		try {
			RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
			
			
			while (true) {
				ativo = arqComp.readChar();
				marca = arqComp.readUTF();
				codComp = arqComp.readUTF();
				modelo  = arqComp.readUTF();
				processador = arqComp.readUTF();
				quantMemoria = arqComp.readInt();
				tamanhoTela = arqComp.readInt();
				quantEstoque = arqComp.readInt();
				preco = arqComp.readFloat();
				quantVendida = arqComp.readInt();
				dtUltimaVenda = arqComp.readUTF();
				
				if(marca.equals(novaMarca) && ativo == 's') {
					estoqueAtual = quantEstoque;
					
				}
			}
			
			
			
		} catch (IOException e) { 
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
		}
	}
	

	// ***********************   Registrar   *****************************
	public void Registrar() {
		String compChave;
		char confirmacao;
		String continua;

		do {

			ativo = 'S';
			
			System.out.print("Digite a marca do Computador.........................: ");
			marca = leia.nextLine(); 
			
			System.out.print("Digite o modelo do comoutador.......: ");
			modelo = leia.nextLine();	
			
			System.out.print("Digite o tipo do processador..................: ");
			processador = leia.nextLine();
			
			System.out.print("Digite a quantida de memoria RAM...................: ");
			quantMemoria = leia.nextInt();
			
			System.out.print("Digite o tamanho da tela: ");
			tamanhoTela = leia.nextInt();
			
			System.out.print("Digite a quantidade de estoque:  ");
			quantEstoque = leia.nextInt();
			
			System.out.print("Digite o preco do computador: ");
			preco = leia.nextFloat();
			
			do {
				System.out.print("\nConfirma a gravacao dos dados (S/N) ? ");
				confirmacao = leia.next().charAt(0);
				if (confirmacao == 'S') {
					salvarComputador();
				}
			}while (confirmacao != 'S' && confirmacao != 'N');
			
			System.out.println("Adicionar novo computador? [S]");
			continua = leia.nextLine();

		}while (continua.equalsIgnoreCase("s"));	    
	}
	

	//************************  ALTERACAO  *****************************
	public void alterar() {
		String codComp;
		char confirmacao;
		long posicaoRegistro = 0;
		byte opcao;

		do {
			do {
				Main.leia.nextLine();
				System.out.println("\n ***************  ALTERACAO DE ALUNOS  ***************** ");
				System.out.print("Digite o computador que deseja alterar( FIM para encerrar ): ");
				codComp = leia.nextLine();
				if (codComp.equals("FIM")) {
					break;
				}

				posicaoRegistro = pesquisarComputador(codComp);
				if (posicaoRegistro == -1) {
					System.out.println("Matricula nao cadastrada no arquivo, digite outro valor\n");
				}
			}while (posicaoRegistro == -1);

			if (codComp.equals("FIM")) {
				break;
			}

			ativo = 'S';
//			na tela de alteração, os seus valores deverão ser exibidos, mas não poderão ser alterados.
//			marca - consistir por meio do método consistirMarca;
//			modelo - digitação obrigatória ;
//			processador - consistir por meio da função consistirProcessador;
//			quantMemoria - aceitar somente valores entre 1 e 16 (valores em GB);
//			tamanhoTela - consistir por meio da função consistirTamanhoTela;

			do {
				System.out.println("[ 1 ] Marca do computador............: " + marca);
				System.out.println("[ 2 ] Modelo do computador ......: " + modelo);
				System.out.println("[ 3 ] Processador.....: " + processador);
				System.out.println("[ 4 ] Quantidade de memoria RAM ............: " + quantMemoria);
				System.out.println("[ 5 ] Tamanho da tela.....: " + tamanhoTela);
				System.out.println("[ 6 ] Quantidade de estoque.....: " + tamanhoTela);
				System.out.println("[ 7 ] preco.....: " + preco);

				do{
					System.out.println("Digite o numero do campo que deseja alterar (0 para finalizar as alteraÃ§Ãµes): ");
					opcao = Main.leia.nextByte();
				}while (opcao < 0 || opcao > 7);

				switch (opcao) {
				case 1:
					leia.nextLine();
					do {
						
						System.out.println("Digite a marcar que deseja alterar..................: ");
						marca = leia.nextLine();
						
						if(! checarMarca()) {
							System.out.println("Marca inexistente");
						}
					}while(!checarMarca());
					
					try {
						RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
						
					}catch(IOException e) {
						System.out.println("Erro na abertura do arquivo");
					}
					
					break;
				case 2: 
					leia.nextLine();
					System.out.println("Digite o modelo de computador: ");
					modelo = leia.nextLine();
					break;
				case 3:
					do {
						System.out.println("Digite o novo processador...........: ");
						processador = leia.nextLine();
						if(!checarProcessadores()) {
							System.out.println("Processador invalido");
						}
					}while(!checarProcessadores());
					break;
				case 4: 
					System.out.println("Digite a quantidade de memoria RAM")............: ");
					quantMemoria = leia.nextInt();
					break;
				}
				System.out.println();
			}while (opcao != 0);  		

			do {
				System.out.print("\nConfirma a alteracao dos dados (S/N) ? ");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					desativarAluno(posicaoRegistro);
					salvarComputador();
					System.out.println("Dados gravados com sucesso !\n");
				}
			}while (confirmacao != 'S' && confirmacao != 'N');

		}while ( ! matricula.equals("FIM"));
	}


	//************************  EXCLUSAO  *****************************
	public void excluir() {
		String matriculaChave;
		char confirmacao;
		long posicaoRegistro = 0;

		do {
			do {
				Main.leia.nextLine();
				System.out.println(" ***************  EXCLUSAO DE ALUNOS  ***************** ");
				System.out.print("Digite a Matricula do Aluno que deseja excluir ( FIM para encerrar ): ");
				matriculaChave = Main.leia.nextLine();
				if (matriculaChave.equals("FIM")) {
					break;
				}

				posicaoRegistro = pesquisarAluno(matriculaChave);
				if (posicaoRegistro == -1) {
					System.out.println("Matricula nao cadastrada no arquivo, digite outro valor\n");
				}
			}while (posicaoRegistro == -1);

			if (matriculaChave.equals("FIM")) {
				System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
				break;
			}

			System.out.println("Nome do aluno.......: " + nomeAluno);
			System.out.println("Data de nascimento..: " + dtNasc);
			System.out.println("Valor da mensalidade: " + mensalidade);
			System.out.println("Sexo do aluno.......: " + sexo);
			System.out.println();

			do {
				System.out.print("\nConfirma a exclusao deste aluno (S/N) ? ");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					desativarAluno(posicaoRegistro);
				}
			}while (confirmacao != 'S' && confirmacao != 'N');

		}while ( ! matricula.equals("FIM"));
	}

	//************************  CONSULTA  *****************************
	public void consultar() 	{
		RandomAccessFile arqComp;
		byte opcao;
		String matriculaChave;
		char sexoAux;
		long posicaoRegistro;

		do {
			do {
				System.out.println(" ***************  CONSULTA DE ALUNOS  ***************** ");
				System.out.println(" [1] CONSULTAR APENAS 1 ALUNO ");
				System.out.println(" [2] LISTA DE TODOS OS ALUNOS ");
				System.out.println(" [3] LISTA SOMENTE SEXO MASCULINO OU FEMININO ");
				System.out.println(" [0] SAIR");
				System.out.print("\nDigite a opcao desejada: ");
				opcao = Main.leia.nextByte();
				if (opcao < 0 || opcao > 3) {
					System.out.println("opcao Invalida, digite novamente.\n");
				}
			}while (opcao < 0 || opcao > 3);

			switch (opcao) {
			case 0:
				System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
				break;

			case 1:  // consulta de uma unica matricula
				Main.leia.nextLine();  // limpa buffer de memoria
				System.out.print("Digite a Matriocula do Aluno: ");
				matriculaChave = Main.leia.nextLine();

				posicaoRegistro = pesquisarAluno(matriculaChave);
				if (posicaoRegistro == -1) {
					System.out.println("Matricula nao cadastrada no arquivo \n");
				} else {
					imprimirCabecalho();
					imprimirAluno();
					System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
					Main.leia.nextLine();
				}

				break;

			case 2:  // imprime todos os alunos
				try { 
					arqComp = new RandomAccessFile("COMP.DAT" , "rw");
					imprimirCabecalho();
					while (true) {
						ativo		= arqComp.readChar();
						matricula   = arqComp.readUTF();
						nomeAluno   = arqComp.readUTF();
						dtNasc      = arqComp.readUTF();
						mensalidade = arqComp.readFloat();
						sexo        = arqComp.readChar();
						if ( ativo == 'S') {
							imprimirAluno();
						}
					}
					//    arqComp.close();
				} catch (EOFException e) {
					System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
					Main.leia.nextLine();
					matriculaChave = Main.leia.nextLine();
				} catch (IOException e) { 
					System.out.println("Erro na abertura do arquivo - programa sera finalizado");
					System.exit(0);
				}
				break;

			case 3:  // imprime alunos do sexo desejado
				do {
					System.out.print("Digite o Sexo desejado (M/F): ");
					sexoAux = Main.leia.next().charAt(0);
					if (sexoAux != 'F' && sexoAux != 'M') {
						System.out.println("Sexo Invalido, digite M ou F");
					}
				}while (sexoAux != 'F' && sexoAux != 'M');

				try { 
					arqComp = new RandomAccessFile("COMP.DAT", "rw");
					imprimirCabecalho();
					while (true) {
						ativo		= arqComp.readChar();
						matricula   = arqComp.readUTF();
						nomeAluno   = arqComp.readUTF();
						dtNasc      = arqComp.readUTF();
						mensalidade = arqComp.readFloat();
						sexo        = arqComp.readChar();

						if ( sexoAux == sexo && ativo == 'S') {
							imprimirAluno();
						}
					}
				} catch (EOFException e) {
					System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
					Main.leia.nextLine();
					matriculaChave = Main.leia.nextLine();
				} catch (IOException e) { 
					System.out.println("Erro na abertura do arquivo - programa sera finalizado");
					System.exit(0);
				}

			}	

		} while ( opcao != 0 );
	}

	public void imprimirCabecalho () {
		System.out.println("-MATRICULA-  -------- NOME ALUNO ----------  --DATA NASC--  -Mensalidade-  -sexo- ");
	}

	public void imprimirAluno () {
		System.out.println(	formatarString(matricula, 11 ) + "  " +
				formatarString(nomeAluno , 30) + "  " + 
				formatarString(dtNasc , 13) + "  " + 
				formatarString( String.valueOf(mensalidade) , 13 ) + "  " +
				formatarString( Character.toString(sexo) , 6 )   ); 
	}

	public static String formatarString (String texto, int tamanho) {	
		// retorna uma string com o numero de caracteres passado como parametro em TAMANHO
		if (texto.length() > tamanho) {
			texto = texto.substring(0,tamanho);
		}else{
			while (texto.length() < tamanho) {
				texto = texto + " ";
			}
		}
		return texto;
	}
}
