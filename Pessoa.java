import java.util.Random;

public class Pessoa {
    int id;
    String sexo;
    int idade;
    boolean gestante;
    boolean lactante;
    boolean necessidadeEspecial;


    public Pessoa(int id, String sexo, int idade, boolean gestante, boolean lactante, boolean necessidadeEspecial) {
        this.id = id;
        this.sexo = sexo;
        this.idade = idade;
        this.gestante = gestante;
        this.lactante = lactante;
        this.necessidadeEspecial = necessidadeEspecial;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isGestante() {
        return this.gestante;
    }

    public boolean getGestante() {
        return this.gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public boolean isLactante() {
        return this.lactante;
    }

    public boolean getLactante() {
        return this.lactante;
    }

    public void setLactante(boolean lactante) {
        this.lactante = lactante;
    }

    public boolean isNecessidadeEspecial() {
        return this.necessidadeEspecial;
    }

    public boolean getNecessidadeEspecial() {
        return this.necessidadeEspecial;
    }

    public void setNecessidadeEspecial(boolean necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public static Pessoa gerarPessoa(Random random) {
        int limitePessoasGeradas = 100;
        int contador = 0;
    
        while (contador < limitePessoasGeradas) {
            int id = random.nextInt(1000);
            String sexo = random.nextBoolean() ? "Masculino" : "Feminino";
            int idade = random.nextInt(100);
            boolean gestante = random.nextBoolean();
            boolean lactante = random.nextBoolean();
            boolean necessidadeEspecial = random.nextBoolean();
            contador++;
    
            if (contador <= limitePessoasGeradas) {
                return new Pessoa(id, sexo, idade, gestante, lactante, necessidadeEspecial);
            }
        }
        return null;
    }
    

    public int getPrioridade() {
        if (idade >= 60) {
            return 1;
        } else if (necessidadeEspecial) {
            return 2;
        } else if (gestante || lactante) {
            return 3;
        } else {
            return 0;
        }
    }

    public static Pessoa atenderPessoa(Deque<Pessoa> deque) {
        int prioridadeMaxima = 3;

        for (int i = prioridadeMaxima; i >= 0; i--) {
            Deque<Pessoa> pessoasComPrioridade = new Deque<>();

            while (!deque.isEmpty()) {
                Pessoa pessoa = deque.removeDoInicio();
                if (pessoa.getPrioridade() == i) {
                    while (!pessoasComPrioridade.isEmpty()) {
                        deque.adicionaNoInicio(pessoasComPrioridade.removeDoFim());
                    }
                    return pessoa;
                } else {
                    pessoasComPrioridade.adicionaNoFinal(pessoa);
                }
            }
            while (!pessoasComPrioridade.isEmpty()) {
                deque.adicionaNoInicio(pessoasComPrioridade.removeDoFim());
            }
        }
        return null;
    }
}