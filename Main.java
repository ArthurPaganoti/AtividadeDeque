import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Deque<Pessoa> deque = new Deque<>();
        Queue<Pessoa> naoAtendidas = new LinkedList<>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int totalAtendimentos = 100;
        int pessoasAtendidas = 0;
        int grupo = 1;
        int pessoasNoGrupo = 1;

        while (pessoasAtendidas < totalAtendimentos) {
            for (int i = 0; i < pessoasNoGrupo; i++) {
                if (pessoasAtendidas < totalAtendimentos) {
                    Pessoa pessoa = Pessoa.gerarPessoa(random);
                    deque.adicionaNoFinal(pessoa);
                    pessoasAtendidas++;
                    System.out.println("Pessoa " + pessoa.getId() + " adicionada à fila de atendimento.");
                } else {
                    break;
                }
            }
            pessoasNoGrupo++;
        }
        System.out.println("\nAtendimentos realizados no posto de saúde:");
        while (!deque.isEmpty()) {
            Pessoa pessoaAtendida = Pessoa.atenderPessoa(deque);
            if (pessoaAtendida != null) {
                System.out.println("Pessoa " + pessoaAtendida.getId() + " atendida.");
            } else {
                System.out.println("Não há mais pessoas para atender.");
                break;
            }
        }

        System.out.println("\nPessoas que não foram atendidas:");
        while (!deque.isEmpty()) {
            Pessoa pessoaNaoAtendida = deque.removeDoInicio();
            naoAtendidas.offer(pessoaNaoAtendida);
            System.out.println("Pessoa " + pessoaNaoAtendida.getId());
        }
    }
}