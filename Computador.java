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
        char confirmacao;
        byte opcao;
        int maiorCod = 0;
        String marcaAux = "";

       do{ 

        System.out.println("Digite a marca que deseja incluir estoque(FIM PARA ENCERRAR A INCLUSÃO): ");
        marcaAux = leia.nextLine();


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

            System.out.print("Digite o modelo do computador.......: ");
            modelo = leia.nextLine();

            System.out.print("Digite o tipo do processador..................: ");
            processador = leia.nextLine();

            System.out.print("Digite a quantida de memoria RAM...................: ");
            quantMemoria = leia.nextInt();

            System.out.print("Digite o tamanho da tela: ");
            tamanhoTela = leia.nextInt();

            System.out.print("Digite a quantidade de estoque:  ");
            quantEstoque = leia.nextInt();

            System.out.print("Digite o preço do computador: ");
            preco = leia.nextFloat();

            do {
                System.out.print("\nConfirma a gravacao dos dados (S/N) ? ");
                confirmacao = leia.next().charAt(0);
                if (confirmacao == 'S') {
                    salvarComputador();
                }
            }while (confirmacao != 'S' && confirmacao != 'N');
            
            leia.nextLine();
            
        }while (! codComp.equalsIgnoreCase("FIM"));
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
        RandomAccessFile arqComp;
        byte opcao;
        String compChave;
        char pesqAux;
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
                    imprimirRelatorio();

                    break;

                case 2:  // imprime todos os computadores atraves do registro
                	
                	Main.leia.nextLine();  // limpa buffer de memoria
                    System.out.print("Digite o codigo do computador: ");
                    compChave = Main.leia.nextLine();
                    
                    posicaoRegistro = pesquisarComputador(compChave);
                    
                    if (posicaoRegistro == -1) {
                        System.out.println("Matricula nao cadastrada no arquivo \n");
                    } else {
                        imprimirCabecalho();
                        imprimirRelatorio();
                        System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
                        Main.leia.nextLine();
                    }
                    break;

                case 3:
                	 posicaoRegistro = pesquisarComputador(compChave);
                	if(ativo == 'S' && quantVendida > 0) {
                		imprimirCabecalho();
                		imprimirRelatorio();
                	}
                	else {
                		System.out.println("Nenhum computador nao foi vendido");
                	}
                	break;
                	
                case 4:
                	System.out.println("Insira o mes e o ano(MM/AA): "); // 12/09/22
                	data = leia.next();
                	
                	posicaoRegistro = pesquisarComputador(compChave);
                	
                	if(ativo == 'S' && data.equalsIgnoreCase(dtUltimaVenda.substring(3))){
                		imprimirCabecalho();
                		imprimirRelatorio();
                	}
                	
                case 5:
                	System.out.println("Digite uma faixa de preço mínima: ");
                	minimo = leia.nextFloat();
                	
                	System.out.println("Digite uma faixa de preço máximo: ");
                	maximo = leia.nextFloat();
                	
                	posicaoRegistro = pesquisarComputador(compChave);
                	
                	if(ativo == 'S' && preco >= minimo && preco <= maximo) {
                		imprimirCabecalho();
                		imprimirRelatorio();
                	}
                	
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

    public void imprimirSomatorio() {
    	
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
