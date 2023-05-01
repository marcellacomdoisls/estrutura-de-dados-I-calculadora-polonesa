import java.util.Scanner;

public class Principal {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    Calculadora calcular = new Calculadora();

    // solicita a entrada do usuário  
    System.out.print("Digite a expressão matemática: ");
    String expressao = teclado.nextLine();

    // solicita a notação da expressão
    System.out.print("Digite a notação da expressão (infixa, pós-fixa ou pré-fixa): ");
    String notacao = teclado.nextLine();

    // realiza o cálculo e apresenta as expressões nas três notações
    switch (notacao) {
      case "infixa":
        String expressaoPosFixa = calcular.infixaParaPosFixa(expressao);
        String expressaoPreFixa = calcular.infixaParaPreFixa(expressao);
        System.out.println("Pós-fixa: " + expressaoPosFixa);
        System.out.println("Pré-fixa: " + expressaoPreFixa);
        break;
      case "pós-fixa":
        String expressaoInfixa = calcular.posFixaParaInfixa(expressao);
        expressaoPreFixa = calcular.posFixaParaPreFixa(expressao);
        System.out.println("Infixa: " + expressaoInfixa);
        System.out.println("Pré-fixa: " + expressaoPreFixa);
        break;
      case "pré-fixa":
        expressaoInfixa = calcular.preFixaParaInfixa(expressao);
        expressaoPosFixa = calcular.preFixaParaPosFixa(expressao);
        System.out.println("Infixa: " + expressaoInfixa);
        System.out.println("Pós-fixa: " + expressaoPosFixa);
        break;
      default:
        System.out.println("Notação inválida.");
        break;
    }
    teclado.close();
  }
}
