/*
 * Se for usar este código, cite o autor.
 */
package tradutorias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Instrucao {
    public int opcode;
    public Simbolo parametro;

    public Instrucao(int opcode, Simbolo parametro) {
        this.opcode = opcode;
        this.parametro = parametro;
    }
    
    public Instrucao(int opcode) {
        this.opcode = opcode;
        this.parametro = null;
    }
    
}

enum Operador {
    Adicao,
    Subtracao,
    Multiplicacao,
    Divisao,
    Resto,
    Maior,
    Menor,
    MaiorOuIgual,
    MenorOuIgual,
    Igual,
    ConjuncaoLogica,
    DisjuncaoLogica,
    NegacaoLogica
}

enum TipoSimbolo {
    Variavel,
    Constante,
    RotuloParaSalto
}

class Simbolo {
    public String token;
    public TipoSimbolo tipo;
    public long valor;
    public Instrucao destinoSalto;

    public Simbolo(String token, TipoSimbolo tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public Simbolo(String token, TipoSimbolo tipo, long valor) {
        this.token = token;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Simbolo(String token, TipoSimbolo tipo, Instrucao destinoSalto) {
        this.token = token;
        this.tipo = tipo;
        this.destinoSalto = destinoSalto;
    }
}

class OpCode {
    private static String[] instrucoesLinguagemModificada = {
        "STOP",
        "LOAD M(X)",
        "LOAD -M(X)",
        "LOAD |M(X)|",
        "LOAD -|M(X)|",
        "ADD M(X)",
        "SUB M(X)",
        "ADD |M(X)|",
        "SUB |M(X)|",
        "LOAD MQ,M(X)",
        "LOAD MQ",
        "MUL M(X)",
        "DIV M(X)",
        "JUMP M(X,0:19)",
        "JUMP M(X,20:39)",
        "JUMP+ M(X,0:19)",
        "JUMP+ M(X,20:39)",
        "STOR M(X)",
        "STOR M(X,8:19)",
        "STOR M(X,28:39)",
        "LSH",
        "RSH",
        "JUMP M(X,...)",
        "JUMP+ M(X,...)"
    };
    private static String[] instrucoesLinguagemOriginal = {
        "halt",
        "S(x)->Ac+",
        "S(x)->Ac-",
        "S(x)->AcM",
        "S(x)->Ac-M",
        "S(x)->Ah+",
        "S(x)->Ah-",
        "S(x)->AhM",
        "S(x)->Ah-M",
        "S(x)->R",
        "R->A",
        "S(x)*R->A",
        "A/S(x)->R",
        "Cu->S(x)",
        "Cu'->S(x)",
        "Cc->S(x)",
        "Cc'->S(x)",
        "At->S(x)",
        "Ap->S(x)",
        "Ap'->S(x)",
        "L",
        "R"
    };
    
    public static int getOpCode(String instrucao) {
        int opcode = -1;
        int i = 0;
        for (String instr : instrucoesLinguagemModificada) {
            if (instr.equals(instrucao)) {
                opcode = i;
                break;
            }
            i++;
        }
        return opcode;
    }
    
    public static String getInstrucaoLinguagemModificada(int opcode) {
        return instrucoesLinguagemModificada[opcode];
    }
    
    public static String getInstrucaoLinguagemOriginal(int opcode) {
        return instrucoesLinguagemOriginal[opcode];
    }
}

/**
 * O TradutorIAS é um programa que lê um arquivo na entrada, em linguagem de 
 * alto nível (semelhante à linguagem do VisuAlg, mas simplificada), e escreve 
 * na saída as instruções para programar o IAS para executar os comandos da 
 * entrada. Nesta versão, é obrigatório o uso de um espaço em branco entre cada
 * elemento léxico dos comandos.
 * 
 * @author Alexandre Romanelli <alexandre.romanelli@ifes.edu.br>
 */
public class TradutorIAS {

    private static ArrayList<Simbolo> tabelaSimbolos;
    private static ArrayList<Instrucao> instrucoes;
    private static int contagemVariaveisTemporarias = 0;
    private static int contagemRotulosSaltos = 0;
    private static boolean haSaltoIndefinido = false;
    private static ArrayList<Simbolo> saltosIndefinidos = new ArrayList<>();
    
    /**
     * Para testar o programa com o exercício proposto, este método insere
     * símbolos pré-definidos na tabela.
     */
    private static void preparaTabelaSimbolos() {
        tabelaSimbolos.add(new Simbolo("A", TipoSimbolo.Variavel));
        tabelaSimbolos.add(new Simbolo("B", TipoSimbolo.Variavel));
        tabelaSimbolos.add(new Simbolo("C", TipoSimbolo.Variavel));
        tabelaSimbolos.add(new Simbolo("D", TipoSimbolo.Variavel));
        tabelaSimbolos.add(new Simbolo("C1", TipoSimbolo.Constante, -1));
        tabelaSimbolos.add(new Simbolo("C2", TipoSimbolo.Constante, 2));
        tabelaSimbolos.add(new Simbolo("C3", TipoSimbolo.Constante, 5));
        tabelaSimbolos.add(new Simbolo("C4", TipoSimbolo.Constante, 17));
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("no-gui")) {
            tabelaSimbolos = new ArrayList<>();
            instrucoes = new ArrayList<>();

            // para testes com exercícios
            preparaTabelaSimbolos();

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // ler e traduzir os comandos
            processarComandos(input);

            // adicionar instruçao STOP
            adicionarInstrucao(instrucoes, new Instrucao(OpCode.getOpCode("STOP")));

            // gerar codigo
            gerarCodigo();
        }
    }
    
    private static String processarComandos(BufferedReader input) throws IOException {
        String linha;
        while ((linha = input.readLine()) != null && 
                !(linha.trim().length() >= 3 && linha.trim().substring(0, 3).equals("FIM")) &&
                !linha.trim().equals("SENAO")) {
            linha = linha.trim();
            // decodificar o comando
            String[] tokens = linha.split(" ");
            if (tokens.length < 3)
                continue;
            
            if (tokens[0].equals("SE")) {
                processarSe(tokens, input);
            } else if (tokens[0].equals("ENQUANTO")) {
                processarEnquanto(tokens, input);
            } else if (tokens[1].equals("<-")) {
                processarAtribuicao(tokens);
            }
        }
        return linha;
    }
    
    private static Simbolo processarExpressao(String[] tokens, 
            int limiteInicial, int limiteFinal) {
        // interpretar expressão (assumir totalmente parentetizada)
        Stack<Simbolo> pilhaOperandos = new Stack();
        Stack<Operador> pilhaOperadores = new Stack();
        for (int i = limiteInicial; i < limiteFinal; i++) {
            Simbolo simboloToken;
            if (tokenNumerica(tokens[i])) { // é literal numérica
                simboloToken = procurarItemTabelaSimbolos(tokens[i]);
                if (simboloToken == null) {
                    simboloToken = new Simbolo(tokens[i], TipoSimbolo.Constante, Long.parseLong(tokens[i]));
                    tabelaSimbolos.add(simboloToken);
                }
                // empilhar operando
                pilhaOperandos.push(simboloToken);
            } else if ((simboloToken = procurarItemTabelaSimbolos(tokens[i])) != null) { // é variável
                // empilhar operando
                pilhaOperandos.push(simboloToken);
            } else if (tokens[i].equals(">") || tokens[i].equals("<") || tokens[i].equals(">=") || tokens[i].equals("<=") || 
                    tokens[i].equals("=") || tokens[i].equals("E") || tokens[i].equals("OU") || tokens[i].equals("NAO") || 
                    tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/") || 
                    tokens[i].equals("%")) {
                // identificar operador
                Operador op = Operador.Adicao; // só para inicializar mesmo, é arbitrário
                switch (tokens[i]) {
                    case "+":
                        op = Operador.Adicao;
                        break;
                    case "-":
                        op = Operador.Subtracao;
                        break;
                    case "*":
                        op = Operador.Multiplicacao;
                        break;
                    case "/":
                        op = Operador.Divisao;
                        break;
                    case "%":
                        op = Operador.Resto;
                        break;
                    case "<":
                        op = Operador.Menor;
                        break;
                    case ">":
                        op = Operador.Maior;
                        break;
                    case "<=":
                        op = Operador.MenorOuIgual;
                        break;
                    case ">=":
                        op = Operador.MaiorOuIgual;
                        break;
                    case "=":
                        op = Operador.Igual;
                        break;
                    case "E":
                        op = Operador.ConjuncaoLogica;
                        break;
                    case "OU":
                        op = Operador.DisjuncaoLogica;
                        break;
                    case "NAO":
                        op = Operador.NegacaoLogica;
                        break;
                    default:
                        break;
                }
                // empilhar operador
                pilhaOperadores.push(op);
            } else if (tokens[i].equals(")")) {
                // extrair operandos e operador
                Operador op = pilhaOperadores.pop();
                Simbolo operando1 = null, operando2 = null;
                if (op == Operador.NegacaoLogica) { // apenas um operando
                    operando1 = pilhaOperandos.pop();
                } else { // dois operandos
                    operando2 = pilhaOperandos.pop();
                    operando1 = pilhaOperandos.pop();
                }

                // preparar variável temporária para receber resultado da sub-expressão
                Simbolo temporaria = new Simbolo(("T#" + (contagemVariaveisTemporarias++)), TipoSimbolo.Variavel);
                tabelaSimbolos.add(temporaria);
                
                // codificar operação na lista de instruções
                switch (op) {
                    case Adicao:
                        // LOAD M(X) operando1
                        Instrucao ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // ADD M(X) operando2
                        Instrucao ins2 = new Instrucao(OpCode.getOpCode("ADD M(X)"), operando2);
                        // STOR M(X) temporariaN
                        Instrucao ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        break;

                    case Subtracao:
                        // LOAD M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // SUB M(X) operando2
                        ins2 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando2);
                        // STOR M(X) temporariaN
                        ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        break;

                    case Multiplicacao:
                        // LOAD MQ,M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD MQ,M(X)"), operando1);
                        // MUL M(X) operando2
                        ins2 = new Instrucao(OpCode.getOpCode("MUL M(X)"), operando2);
                        // LOAD MQ
                        ins3 = new Instrucao(OpCode.getOpCode("LOAD MQ"));
                        // STOR M(X) temporariaN
                        Instrucao ins4 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        break;

                    case Divisao:
                        // LOAD M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // DIV M(X) operando2
                        ins2 = new Instrucao(OpCode.getOpCode("DIV M(X)"), operando2);
                        // LOAD MQ
                        ins3 = new Instrucao(OpCode.getOpCode("LOAD MQ"));
                        // STOR M(X) temporariaN
                        ins4 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        break;

                    case Resto:
                        // LOAD M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // DIV M(X) operando2
                        ins2 = new Instrucao(OpCode.getOpCode("DIV M(X)"), operando2);
                        // STOR M(X) temporariaN
                        ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        break;

                    case MaiorOuIgual: // operando1 - operando2
                        // LOAD M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // SUB M(X) operando2
                        ins2 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando2);
                        // STOR M(X) temporariaN
                        ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        break;

                    case MenorOuIgual: // operando2 - operando1
                        // LOAD M(X) operando2
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando2);
                        // SUB M(X) operando1
                        ins2 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando1);
                        // STOR M(X) temporariaN
                        ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        break;

                    case Maior: // operando1 - 1 - operando2
                        Simbolo operando3 = procurarItemTabelaSimbolos("1");
                        if (operando3 == null) {
                            operando3 = new Simbolo("1", TipoSimbolo.Constante, 1);
                            tabelaSimbolos.add(operando3);
                        }
                        // LOAD M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // SUB M(X) operando3
                        ins2 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando3);
                        // SUB M(X) operando2
                        ins3 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando2);
                        // STOR M(X) temporariaN
                        ins4 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        break;

                    case Menor: // operando2 - 1 - operando1
                        operando3 = procurarItemTabelaSimbolos("1");
                        if (operando3 == null) {
                            operando3 = new Simbolo("1", TipoSimbolo.Constante, 1);
                            tabelaSimbolos.add(operando3);
                        }
                        // LOAD M(X) operando2
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando2);
                        // SUB M(X) operando3
                        ins2 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando3);
                        // SUB M(X) operando1
                        ins3 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando1);
                        // STOR M(X) temporariaN
                        ins4 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        break;

                    case Igual: // (operando1 - operando2) * (operando2 - operando1)
                        // LOAD M(X) operando1
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        // SUB M(X) operando2
                        ins2 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando2);
                        // STOR M(X) temporariaN
                        ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        // LOAD MQ,M(X) temporariaN
                        ins4 = new Instrucao(OpCode.getOpCode("LOAD MQ,M(X)"), temporaria);
                        // LOAD M(X) operando2
                        Instrucao ins5 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando2);
                        // SUB M(X) operando1
                        Instrucao ins6 = new Instrucao(OpCode.getOpCode("SUB M(X)"), operando1);
                        // STOR M(X) temporariaN
                        Instrucao ins7 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        // MUL M(X) temporariaN
                        Instrucao ins8 = new Instrucao(OpCode.getOpCode("MUL M(X)"), temporaria);
                        // LOAD MQ
                        Instrucao ins9 = new Instrucao(OpCode.getOpCode("LOAD MQ"));
                        // STOR M(X) temporariaN
                        Instrucao ins10 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        adicionarInstrucao(instrucoes, ins5);
                        adicionarInstrucao(instrucoes, ins6);
                        adicionarInstrucao(instrucoes, ins7);
                        adicionarInstrucao(instrucoes, ins8);
                        adicionarInstrucao(instrucoes, ins9);
                        adicionarInstrucao(instrucoes, ins10);
                        break;

                    case ConjuncaoLogica:
                        //    LOAD M(X) operando1
                        //    JUMP+ M(X,...) V
                        //    JUMP M(X,...) F
                        // V: LOAD M(X) operando2
                        // F:
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        Simbolo rotuloV = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
                        ins2 = new Instrucao(OpCode.getOpCode("JUMP+ M(X,...)"), rotuloV);
                        Simbolo rotuloF = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
                        ins3 = new Instrucao(OpCode.getOpCode("JUMP M(X,...)"), rotuloF);
                        ins4 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando2);
                        rotuloV.destinoSalto = ins4;
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        // deixar rotuloF como indefinido e pendente para a próxima instrução adicionada
                        haSaltoIndefinido = true;
                        saltosIndefinidos.add(rotuloF);
                        break;
                        
                    case DisjuncaoLogica:
                        //    LOAD M(X) operando1
                        //    JUMP+ M(X,...) V
                        // V: LOAD M(X) operando2
                        // F:
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        rotuloV = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
                        ins2 = new Instrucao(OpCode.getOpCode("JUMP+ M(X,...)"), rotuloV);
                        rotuloF = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
                        ins3 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando2);
                        rotuloV.destinoSalto = ins3;
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        // deixar rotuloF como indefinido e pendente para a próxima instrução adicionada
                        haSaltoIndefinido = true;
                        saltosIndefinidos.add(rotuloF);
                        break;
                        
                    case NegacaoLogica:
                        // LOAD M(X) operando1
                        // ADD M(X) C1
                        // STOR M(X) T1
                        // LOAD -M(X) T1
                        operando3 = procurarItemTabelaSimbolos("1");
                        if (operando3 == null) {
                            operando3 = new Simbolo("1", TipoSimbolo.Constante, 1);
                            tabelaSimbolos.add(operando3);
                        }
                        ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), operando1);
                        ins2 = new Instrucao(OpCode.getOpCode("ADD M(X)"), operando3);
                        ins3 = new Instrucao(OpCode.getOpCode("STOR M(X)"), temporaria);
                        ins4 = new Instrucao(OpCode.getOpCode("LOAD -M(X)"), temporaria);
                        adicionarInstrucao(instrucoes, ins1);
                        adicionarInstrucao(instrucoes, ins2);
                        adicionarInstrucao(instrucoes, ins3);
                        adicionarInstrucao(instrucoes, ins4);
                        break;
                }
                
                // empilhar temporária, que é o resultado da sub-expressão
                pilhaOperandos.push(temporaria);
            }
        }
        
        // desempilhar variável que terá o resultado da expressão do lado direito da atribuição
        Simbolo resultadoExpressao = pilhaOperandos.pop();
        
        return resultadoExpressao;
    }
    
    private static void processarAtribuicao(String[] tokens) {
        // verificar se variável está na tabela de símbolos
        Simbolo simboloVariavelLadoEsquerdo = procurarItemTabelaSimbolos(tokens[0]);
        if (simboloVariavelLadoEsquerdo == null) {
            // se não está na tabela, incluir
            simboloVariavelLadoEsquerdo = new Simbolo(tokens[0], TipoSimbolo.Variavel);
            tabelaSimbolos.add(simboloVariavelLadoEsquerdo);
        }

        // interpretar expressão do lado direito da atribuição (assumir totalmente parentetizada)
        Simbolo resultadoExpressao = processarExpressao(tokens, 2, tokens.length);
        
        // adicionar instruçoes para atribuiçao
        Instrucao ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), resultadoExpressao);
        Instrucao ins2 = new Instrucao(OpCode.getOpCode("STOR M(X)"), simboloVariavelLadoEsquerdo);
        adicionarInstrucao(instrucoes, ins1);
        adicionarInstrucao(instrucoes, ins2);
    }
    
    private static void adicionarInstrucao(ArrayList<Instrucao> instrucoes, Instrucao instrucao) {
        instrucoes.add(instrucao);
        if (haSaltoIndefinido) {
            for (Simbolo saltoIndefinido : saltosIndefinidos)
                saltoIndefinido.destinoSalto = instrucao;
            saltosIndefinidos.clear();
            haSaltoIndefinido = false;
        }
    }
    
    private static boolean tokenNumerica(String token) {
        try {
            Long.parseLong(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static Simbolo procurarItemTabelaSimbolos(String item) {
        Simbolo simboloToken = null;
        for (Simbolo simb : tabelaSimbolos) {
            if (simb.token.equals(item)) {
                simboloToken = simb;
                break;
            }
        }
        return simboloToken;
    }

    private static void processarSe(String[] tokens, BufferedReader input) throws IOException {
        // processar expressão condicional
        Simbolo resultadoExpressao = processarExpressao(tokens, 1, tokens.length - 1);

        // LOAD M(X) resultadoExpressao
        Instrucao ins1 = new Instrucao(OpCode.getOpCode("LOAD M(X)"), resultadoExpressao);
        instrucoes.add(ins1);
        // JUMP+ M(X,...) SE
        Simbolo rotuloSe = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
        Instrucao ins2 = new Instrucao(OpCode.getOpCode("JUMP+ M(X,...)"), rotuloSe);
        instrucoes.add(ins2);
        // JUMP M(X,...) SENAO
        Simbolo rotuloSenao = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
        Instrucao ins3 = new Instrucao(OpCode.getOpCode("JUMP M(X,...)"), rotuloSenao);
        instrucoes.add(ins3);
        // SE: <comandos>
        // prepara para ajustar rótulo SE para a próxima instrução adicionada
        haSaltoIndefinido = true;
        saltosIndefinidos.add(rotuloSe);
        // processar comandos SE:
        String delimitadorSe = processarComandos(input);
        
        // JUMP M(X,...) FIM
        Simbolo rotuloFim = new Simbolo(("R#" + (contagemRotulosSaltos++)), TipoSimbolo.RotuloParaSalto, null);
        Instrucao ins4 = new Instrucao(OpCode.getOpCode("JUMP M(X,...)"), rotuloFim);
        instrucoes.add(ins4);
        // SENAO: <comandos>
        // prepara para ajustar rótulo SENAO para a próxima instrução adicionada
        haSaltoIndefinido = true;
        saltosIndefinidos.add(rotuloSenao);
        // processar comandos SENAO
        processarComandos(input);
        
        // FIM:
        // prepara para ajustar rótulo FIM para a próxima instrução adicionada
        haSaltoIndefinido = true;
        saltosIndefinidos.add(rotuloFim);
    }

    private static void processarEnquanto(String[] tokens, BufferedReader input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void gerarCodigo() {
        // primeiro, deve decidir pelos jumps para instruçoes do lado esquerdo ou direito
        for (Instrucao instr : instrucoes) {
            if (instr.opcode == OpCode.getOpCode("JUMP M(X,...)") || instr.opcode == OpCode.getOpCode("JUMP+ M(X,...)")) {
                Simbolo destino = instr.parametro;
                int indiceDestino = instrucoes.indexOf(destino.destinoSalto);
                if (indiceDestino % 2 == 0) {
                    if (instr.opcode == OpCode.getOpCode("JUMP M(X,...)")) {
                        instr.opcode = OpCode.getOpCode("JUMP M(X,0:19)");
                    } else {
                        instr.opcode = OpCode.getOpCode("JUMP+ M(X,0:19)");
                    }
                } else { 
                    if (instr.opcode == OpCode.getOpCode("JUMP M(X,...)")) {
                        instr.opcode = OpCode.getOpCode("JUMP M(X,20:39)");
                    } else {
                        instr.opcode = OpCode.getOpCode("JUMP+ M(X,20:39)");
                    }
                }
            }
        }
        
        // define endereço da palavra com a instruçao STOP
        int enderecoStop = (instrucoes.size() - 1) / 2; // dividido por 2 porque ha duas instruçoes por palavra
        
        // depois, deve escrever o codigo textual na saida, nos dois formatos (original e modificado)
        System.out.println("\nCodigo do programa com instruçoes da linguagem modificada:\n");
        
        ArrayList<String> codigoInstrucoes = new ArrayList<>();
        ArrayList<Integer> parametrosInstrucoes = new ArrayList<>();
        
        for (Instrucao instr : instrucoes) {
            String codigo = OpCode.getInstrucaoLinguagemModificada(instr.opcode);
            int parametro = 0;
            if (instr.parametro != null && instr.parametro.tipo == TipoSimbolo.RotuloParaSalto) {
                int posPalavraDestinoSalto = instrucoes.indexOf(instr.parametro.destinoSalto) / 2;
                codigo += " " + posPalavraDestinoSalto;
                parametro = posPalavraDestinoSalto;
            } else if (instr.parametro != null) {
                int posPalavraParametro = enderecoStop + 1 + tabelaSimbolos.indexOf(instr.parametro);
                codigo += " " + posPalavraParametro;
                parametro = posPalavraParametro;
            }
            
            System.out.println(codigo);
            codigoInstrucoes.add(OpCode.getInstrucaoLinguagemOriginal(instr.opcode));
            parametrosInstrucoes.add(parametro);
        }
        
        // adicionar codigo ".empty", se instruçao STOP for do lado esquerdo
        if (instrucoes.size() % 2 == 1) {
            System.out.println(".empty");
            codigoInstrucoes.add(".empty");
            parametrosInstrucoes.add(0);
        }
        
        // imprimir dados da tabela de simbolos
        for (Simbolo simb : tabelaSimbolos) {
            System.out.println((simb.tipo == TipoSimbolo.Constante ? "c" : "") + simb.token + ": " +  ".data " + simb.valor);
        }
        
        System.out.println("\nCodigo do programa com instruçoes da linguagem original:\n");
        
        for (int i = 0; i < codigoInstrucoes.size(); i++) {
            if (!codigoInstrucoes.get(i).equals(".empty") && instrucoes.get(i).parametro != null) {
                String cod = codigoInstrucoes.get(i) + " " + parametrosInstrucoes.get(i);
                System.out.println(cod);
            } else {
                System.out.println(codigoInstrucoes.get(i));
            }
        }
        // imprimir dados da tabela de simbolos
        for (Simbolo simb : tabelaSimbolos) {
            System.out.println((simb.tipo == TipoSimbolo.Constante ? "c" : "") + simb.token + ": " +  ".data " + simb.valor);
        }
        
        System.out.println("\nCodigo do programa em linguagem de maquina (hexadecimal):\n");
        for (int i = 0; i < parametrosInstrucoes.size(); i++) {
            String op = "";
            if (codigoInstrucoes.get(i).equals(".empty")) {
                op = "00";
            } else {
                op = Integer.toHexString(instrucoes.get(i).opcode);
                if (op.length() == 1)
                    op = "0" + op;
            }
            String par = Integer.toHexString(parametrosInstrucoes.get(i));
            while (par.length() < 3)
                par = "0" + par;
            if (i % 2 == 1)
                System.out.print(" ");
            System.out.print(op + " " + par);
            if (i % 2 == 1)
                System.out.println();
        }
        for (Simbolo simb : tabelaSimbolos) {
            String palavra = Long.toHexString(simb.valor);
            if (palavra.length() > 10)
                palavra = palavra.substring(0, 10);
            while (palavra.length() < 10)
                palavra = "0" + palavra;
            System.out.println(palavra);
        }
    }
    
}
