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
    String marcaAux;
    int quantMemoria;
    int tamanhoTela;
    int quantEstoque;
    float preco;
    int quantVendida;
    String dtUltimaVenda; // <-- DATA DA ULTIMA VENDA

    Scanner leia = new Scanner(System.in);

    public long pesquisarComputador (String codCompPesq) {
    	// METODO PARA LOCALIZAR UM REGISTRO NO ARQUIVO EM DISCO
        long posicaoCursorArquivo = 0;
        try {
            RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
            while (true) {
                posicaoCursorArquivo  = arqComp.getFilePointer();	// POSICAO NO INICIO DO ARQUIVO DE REGISTRO
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
            return -1; // REGISTRO NÃO FOI ENCONTRADO
        }catch (IOException e) {
            System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
            System.exit(0);
            return -1;
        }
    }

    public void listarComputadores() {
    	long cursorFim;
    	long cursor;
    	
    	try {
    		RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
    		
    		while(true) {
    			
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
                
                imprimirRelatorio();
                
    		}    		    	
    		
    	}catch (EOFException e) {
    		System.out.println("Fim do Relatorio!");
    	}catch (IOException e) {
    		System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
            System.exit(0);
    	}
    }
    
    public void listarVendidos() {
    	long cursorFim;
    	long cursor;
    	
    	try {
    		RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
    		cursorFim = arqComp.length();
    		cursor = arqComp.getFilePointer();
    		
    		while(cursor >= cursorFim) {
    			
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
                
                if (ativo == 'S' && quantVendida > 0) {
                	imprimirRelatorio();
                }
                
    		}
    		
    		arqComp.close();
    		
    	}catch (IOException e) {
    		System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
            System.exit(0);
    	}
    }
    
    public void ListarPorData(String data) {
    	long cursorFim;
    	long cursor;
    	
    	try {
    		RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
    		cursorFim = arqComp.length();
    		cursor = arqComp.getFilePointer();
    		
    		while(true) {
    			
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
                
                if (ativo == 'S' && data.equals(dtUltimaVenda.substring(3))) {
                	imprimirRelatorio();
                }                
    		}
    		
    	}catch (EOFException e) {
    		System.out.println(" ");
    	}catch (IOException e) {
    		System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
            System.exit(0);
    	}
    }
    
    public void listarPorValor(float minimo, float maximo){
    	long cursorFim;
    	long cursor;
    	
    	try {
    		RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
    		cursorFim = arqComp.length();
    		cursor = arqComp.getFilePointer();
    		
    		while(true) {
    			
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
                
                if(ativo == 'S' && preco >= minimo && preco <= maximo) {
            		imprimirRelatorio();
            	}               
    		}
    		
    	}catch (EOFException e) {    
    		System.out.println(" ");
    	}catch (IOException e) {
    		System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
            System.exit(0);
    	}
    }
    
    public boolean checarProcessadores() {

        for(int x = 0; x < processadoresComp.length; x++) {
            if(processador.equalsIgnoreCase(processadoresComp[x])) {
                return true;
            }
        }
        System.out.println("Processador invalido! digite outro: ");
        return false;
    }

    public boolean checarTelas() {

        for(int x = 0; x < tamanhoTelas.length; x++) {
            if(tamanhoTela == tamanhoTelas[x]) {
                return true;
            }
        }
        System.out.println("Tela invalida! digite outra: ");
        return false;
    }
    
    public boolean checarMarcas() {
    	for(int x = 0; x < marcasComp.length; x++) {
            if(marcaAux.equalsIgnoreCase(marcasComp[x])) {
                return true;
            }
        }
    	System.out.println("Marca invalida! digite outra: ");
    	return false;
    }
    

    public void salvarComputador() {
    	// METODO PARA INCLUIR UM NOVO REGISTRO NO FINAL DO ARQUIVO EM DISCO
        try {
            RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
            arqComp.seek(arqComp.length());  // POSICIONA O PONTEIRO NO FINAL DO ARQUIVO (EOF)
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
    	// METODO PARA ALTERAR O VALOR DO CAMPO "ATIVO" PARA 'N', TORNANDO-O ASSIM O REGISTRO "EXCLUIDO" 
        try {
            RandomAccessFile arqComp = new RandomAccessFile("COMP.DAT", "rw");
            arqComp.seek(posicao);
            arqComp.writeChar('N');   // DESATIVAR O REGISTRO ANTIGO
            arqComp.close();
        }catch (IOException e) {
            System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
            System.exit(0);
        }
    }

    // ***********************   INCLUIR     *****************************

    public void incluir() {

        char confirmacao;
        int maiorCod = 0;
        marcaAux = "";

       do{ 
    	   leia.nextLine();
	    	   
	        System.out.println("Digite a marca que deseja incluir estoque(FIM PARA ENCERRAR A INCLUSÃO): ");
	        marcaAux = leia.nextLine();
	        
	        if (marcaAux.equalsIgnoreCase("FIM")) {
	        	break;
	        }
	                	
	        while(! checarMarcas()) {  
	        	marcaAux = leia.nextLine();
	        }
	
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
	
	                if ( marca.equals(marcaAux) && Integer.parseInt(codComp.substring(2)) > maiorCod && ativo == 'S') {
	                    maiorCod = Integer.parseInt(codComp.substring(2));
	                }
	            }
	            
	        }catch (EOFException e) {
	            maiorCod = maiorCod + 1;
	            codComp = String.valueOf(maiorCod);
	            while(codComp.length() < 4 ){
	                codComp = '0' + codComp;
	            }
	
	           codComp = marcaAux.substring(0, 2).toUpperCase() + codComp;
	
	        } catch (IOException e) {
	            System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
	            System.exit(0);
	        }
        

            ativo = 'S';
            marca = marcaAux;
            quantVendida = 0;
            dtUltimaVenda = "";

            do {
            	System.out.print("Digite o modelo do computador.......: ");
                modelo = leia.nextLine();	
            } while(modelo.equals(""));
            
            do {
            	System.out.print("Digite o tipo do processador..................: ");
                processador = leia.nextLine();	
            } while(! checarProcessadores());
            
            System.out.print("Digite a quantida de memoria RAM...................: ");
            quantMemoria = leia.nextInt();	
            
            while(quantMemoria < 1 || quantMemoria > 16){
            	System.out.println("Quantidade de memoria RAM deve estar entre 1GB e 16GB!");
            	System.out.print("Digite a quantida de memoria RAM...................: ");
            	quantMemoria = leia.nextInt();
            }   	
            
            do {
            	System.out.print("Digite o tamanho da tela: ");
                tamanhoTela = leia.nextInt();	
            } while(! checarTelas());
            
            System.out.print("Digite a quantidade de estoque:  ");
            quantEstoque = leia.nextInt();	            
            	
            while (quantEstoque < 0){
            	System.out.println("Estoque deve ser maior ou igual a 0:  ");
            	System.out.print("Digite a quantidade de estoque:  ");
            	quantEstoque = leia.nextInt();	
            }
            
            System.out.print("Digite o preço do computador: ");
            preco = leia.nextFloat();	
            
       
            while(preco < 1000 || preco > 20000) {
            	System.out.println("O valor deve estar entre R$1000 e R$20000!");
            	System.out.print("Digite o preço do computador: ");
                preco = leia.nextFloat();	
            }
            
            do {
                System.out.print("\nConfirma a gravacao dos dados (S/N) ? ");
                confirmacao = leia.next().charAt(0);
                if (confirmacao == 'S') {
                    salvarComputador();
                }
            }while (confirmacao != 'S' && confirmacao != 'N');
                        
            do {
            	System.out.println("Deseja adicionar outro computador? [S/N]");
            	confirmacao = leia.next().charAt(0);
            }while(confirmacao != 'S' && confirmacao != 'N');
            
            
        }while (! (confirmacao == 'N'));
    }


    //************************  ALTERACAO  *****************************
    public void alterar() {
        String codComp;
        char confirmacao;
        long posicaoRegistro = 0;
        byte opcao;

        do {
            do {
                leia.nextLine();
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

            do {
                System.out.println("[ 1 ] Modelo do computador ......: " + modelo);
                System.out.println("[ 2 ] Processador.....: " + processador);
                System.out.println("[ 3 ] Quantidade de memoria RAM ............: " + quantMemoria);
                System.out.println("[ 4 ] Tamanho da tela.....: " + tamanhoTela);
                System.out.println("[ 5 ] Quantidade de estoque.....: " + tamanhoTela);
                System.out.println("[ 6 ] preco.....: " + preco);

                do{
                    System.out.println("Digite o numero do campo que deseja alterar (0 para finalizar as alteraÃ§Ãµes): ");
                    opcao = leia.nextByte();
                }while (opcao < 0 || opcao > 6);

                switch (opcao) {
                    case 1:
                        leia.nextLine();
                        System.out.println("Digite o modelo de computador: ");
                        modelo = leia.nextLine();

                        break;

                    case 2:
                        do {
                            System.out.println("Digite o novo processador...........: ");
                            processador = leia.nextLine();
                            if(!checarProcessadores()) {
                                System.out.println("Processador invalido");
                            }
                        }while(!checarProcessadores());
                        break;

                    case 3:
                        System.out.println("Digite a quantidade de memoria RAM");
                        quantMemoria = leia.nextInt();
                        break;

                    case 4:
                        do {
                            System.out.println("Digite o tamanho da tela: ");
                            tamanhoTela = leia.nextInt();
                            if(! checarTelas()) {
                                System.out.println("Telas invalidas");
                            }
                        }while(! checarTelas());

                        break;

                    case 5:
                        System.out.println("Digite a quantidade de estoque: ");
                        quantEstoque = leia.nextInt();
                        break;

                    case 6:
                        System.out.println("Digite o preco: ");
                        preco = leia.nextFloat();
                        break;

                }
                System.out.println();
            }while (opcao != 0);

            do {
                System.out.print("\nConfirma a alteracao dos dados (S/N) ? ");
                confirmacao = leia.next().charAt(0);
                if (confirmacao == 'S') {
                    desativarComputador(posicaoRegistro);
                    salvarComputador();
                    System.out.println("Dados gravados com sucesso !\n");
                }
            }while (confirmacao != 'S' && confirmacao != 'N');

           
        }while ( ! codComp.equals("FIM"));
    }


    //************************  EXCLUSAO  *****************************
    public void excluir() {
        String compChave;
        char confirmacao;
        long posicaoRegistro = 0;

        do {
            do {
                Main.leia.nextLine();
                System.out.println(" ***************  EXCLUSAO DE COMPUTADORES  ***************** ");
                System.out.print("Digite o codigo do computador que deseja excluir ( FIM para encerrar ): ");
                compChave = leia.nextLine();

                if (compChave.equals("FIM")) {
                    break;
                }

                posicaoRegistro = pesquisarComputador(compChave);

                if (posicaoRegistro == -1) {
                    System.out.println("Matricula nao cadastrada no arquivo, digite outro valor\n");
                }
            }while (posicaoRegistro == -1);

            if (compChave.equals("FIM")) {
                System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
                break;
            }

            do {
                System.out.print("\nConfirma a exclusao deste computador (S/N) ? ");
                confirmacao = leia.next().charAt(0);
                if (confirmacao == 'S') {
                    desativarComputador(posicaoRegistro);
                }
            }while (confirmacao != 'S' && confirmacao != 'N');

        }while ( ! compChave.equals("FIM"));
    }

    //************************  CONSULTA  *****************************
    public void consultar() 	{
        byte opcao;
        String compChave;
        long posicaoRegistro;
        String data;
        float minimo;
        float maximo;

        do {
            do {
                System.out.println(" ***************  CONSULTA DE COMPUTADORES  ***************** ");
                System.out.println(" [1] LISTAR TODOS OS COMPUTADORES ");
                System.out.println(" [2] LISTAR APENAS UM COMPUTADOR ATRAVÉS DO CODCAMP INFORMADO ");
                System.out.println(" [3] LISTAR SOMENTE COMPUTADORES JÁ VENDIDOS ");
                System.out.println(" [4] LISTAR COMPUTADORES CUJA ÚLTIMA VENDA OCORREU EM DETERMINADO MÊS/ANO ");
                System.out.println(" [5] LISTAR COMPUTADORES POR FAIXA DE PREÇO ");
                System.out.println(" [0] SAIR");

                System.out.print("\nDigite a opcao desejada: ");
                opcao = Main.leia.nextByte();
                if (opcao < 0 || opcao > 5) {
                    System.out.println("opcao Invalida, digite novamente.\n");
                }
            }while (opcao < 0 || opcao > 5);

            switch (opcao) {
                case 0:
                    System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
                    break;

                case 1:  // LISTAR TODOS OS COMPUTADORES
                    imprimirCabecalho();
                    listarComputadores();

                    break;

                case 2:  // IMPRIME TODOS COMPUTADORES ATRAVES DO REGISTRO
                	
                	Main.leia.nextLine();  // LIMPA O BUFFER DE MEMORIA
                    System.out.print("Digite o codigo do computador: ");
                    compChave = Main.leia.nextLine();
                    
                    posicaoRegistro = pesquisarComputador(compChave);
                    
                    if (posicaoRegistro == -1) {
                        System.out.println("Matricula nao cadastrada no arquivo \n");
                    } else {
                        imprimirCabecalho();
                        imprimirRelatorio();
                        System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
                        leia.nextLine();
                    }
                    
                    break;

                case 3: // LISTAR OS JÁ VENDIDOS
                	 imprimirCabecalho();
                	 listarVendidos();
                	 System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
                     leia.nextLine();
                	break;
                	
                case 4: // LISTAR VENDIDOS EM DETERMINADA DATA
                	System.out.println("Insira o mes e o ano(MM/AA): "); // 12/09/22
                	data = leia.next();
                	
                	imprimirCabecalho();
                	ListarPorData(data);
                	System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
                    leia.nextLine();
                	break;
                	
                case 5: // LISTAR POR FAIXA DE PREÇO
                	System.out.println("Digite uma faixa de preço mínima: ");
                	minimo = leia.nextFloat();
                	
                	System.out.println("Digite uma faixa de preço máximo: ");
                	maximo = leia.nextFloat();
                                	
                	imprimirCabecalho();                	
                	listarPorValor(minimo, maximo);
                	System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
                    leia.nextLine();
                	break;                  
            }

        } while ( opcao != 0 );
    }

    public void imprimirCabecalho () {
        System.out.println("-CODCOMP-  -------- MARCA ----------  --MODELO--  -PROCESSADOR-  -ESTOQUE-  -PREÇO-   -QUANT.VEND-   -DT ULT VENDA-   -VLR TOTAL-");
    }

    public void imprimirRelatorio () {
        System.out.println(
                formatarString( codComp , 30 ) + "  " +
                formatarString( marca , 30 ) + "  " +
                formatarString( modelo , 30 ) + "  " +
                formatarString( processador , 30 ) + "  " +
                formatarString( String.valueOf(quantEstoque) , 10 ) + "  " +
                formatarString( String.valueOf(preco) , 10 ) + "  " +
                formatarString( String.valueOf(quantVendida) , 10 ) + "  " +
                formatarString( String.valueOf (dtUltimaVenda) , 10 ) + "  " +
                formatarString( String.valueOf(preco * quantVendida) , 10 ) + "  ");
    }

    public static String formatarString (String texto, int tamanho) {
    	// RETORNA UMA STRING COM O NUMERO DE CARACTERES PASSADO COMO PARAMETRO EM TAMANHO 
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
