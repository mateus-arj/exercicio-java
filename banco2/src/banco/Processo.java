package banco;

import java.time.LocalDate;
import java.util.Scanner;

public class Processo {
    // Variáveis organizadas
    private String tipo;
    private String dono;
    private double saldo;
    private Scanner teclado = new Scanner(System.in);

    public void iniciarAtendimento() {
        System.out.println("Olá, eu sou seu atendente do banco e vou precisar de algumas informações.");

        abrirConta();

        System.out.println("\nPra começarmos, digite seu nome completo: ");
        dono = teclado.nextLine(); // Usando nextLine para nomes compostos

        verificarIdade();
        concederBonusInicial();
        executarMenuPrincipal();
    }

    private void abrirConta() {
        while (true) {
            System.out.println("Qual tipo de conta você quer abrir? [conta corrente (CC)] ou [conta poupança (CP)]");
            tipo = teclado.nextLine().trim().toUpperCase(); // Transforma em maiúsculo para facilitar

            if (tipo.equals("CC")) {
                System.out.println("Parabéns, você acabou de começar a criação de sua conta corrente. R$50,00 serão adicionados caso conclua.");
                break;
            } else if (tipo.equals("CP")) {
                System.out.println("Parabéns, você acabou de começar a criação da sua conta poupança. R$150,00 serão adicionados caso conclua.");
                break;
            } else {
                System.out.println("Opção inválida, escolha outra.");
            }
        }
    }

    private void verificarIdade() {
        System.out.println("Seja bem-vindo " + dono + ", agora digite o ano de seu nascimento: ");
        while (!teclado.hasNextInt()) {
            System.out.println("Erro: Isso não é um número válido! Tente novamente: ");
            teclado.next();
        }
        int nasc = teclado.nextInt();
        teclado.nextLine(); // Limpa o buffer do teclado após ler número

        int dataAtual = LocalDate.now().getYear();
        int idade = dataAtual - nasc;

        if (idade < 18) {
            System.out.println("Você tem " + idade + " anos. Menores de idade não podem abrir conta sozinhos.");
            System.exit(0);
        } else {
            System.out.println("Você está apto para ter uma conta!");
        }
    }

    private void concederBonusInicial() {
        if (tipo.equals("CC")) {
            System.out.println("Sua conta Corrente foi criada! Você recebeu um bônus de R$50,00.");
            saldo += 50;
        } else {
            System.out.println("Sua conta Poupança foi criada! Você recebeu um bônus de R$150,00.");
            saldo += 150;
        }
    }

    private void executarMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nVocê deseja fazer alguma operação agora? [S para Sim / N para Não]");
            String simNao = teclado.next().trim().toUpperCase();

            if (simNao.equals("S")) {
                System.out.println("Escolha: Depositar [1] | Sacar [2] | Ver Saldo [3]");
                while (!teclado.hasNextInt()) {
                    System.out.println("Por favor, digite um número válido (1, 2 ou 3):");
                    teclado.next();
                }
                int escolha = teclado.nextInt();

                if (escolha == 1) {
                    System.out.println("Valor a ser depositado: ");
                    double depositado = teclado.nextDouble();
                    saldo += depositado;
                    System.out.printf("Depósito de R$%.2f realizado com sucesso! Saldo atual: R$%.2f\n", depositado, saldo);
                } else if (escolha == 2) {
                    System.out.println("Valor a ser sacado: ");
                    double sacado = teclado.nextDouble();
                    if (saldo < sacado) {
                        System.out.println("Saldo insuficiente para essa operação.");
                    } else {
                        saldo -= sacado;
                        System.out.printf("Saque de R$%.2f realizado! Restou em conta: R$%.2f\n", sacado, saldo);
                    }
                } else if (escolha == 3) {
                    System.out.printf("Seu saldo atual é: R$%.2f\n", saldo);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } else if (simNao.equals("N")) {
                System.out.println("Obrigado por utilizar nosso banco! Até logo.");
                continuar = false; // Quebra o laço while de forma segura
            } else {
                System.out.println("Opção inválida. Digite S ou N.");
            }
        }
    }
}