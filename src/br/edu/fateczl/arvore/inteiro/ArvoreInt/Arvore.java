package br.edu.fateczl.arvore.inteiro.ArvoreInt;

public class Arvore {
	
	No raiz;
	
	//Inicializando a �rvore
	public Arvore() {
		raiz = null;
	}
	
	//Insert - Inser��o
	
	//Pega o valor da raiz e verificamos se � um valor maior ou menor que a raiz, para sabermos
	//se faremos inser��o � direita (se maior) ou � esquerda (se menor)!
	//Tem que testar se h� algum No � direita ou � esquerda. E, havendo, saber se � maior ou menor que a raiz.
	//M�todo de verifica��o, recursivamente. E, a princ�pio, temos que usar o private.
	private void insert(No no, No raizSubArvore) {
		if(raiz == null) {//Se for nula, meu No � a raiz! //Breakpoint para testar o Insert
			raiz = no;
		} else {//Havendo!
			if(no.dado < raizSubArvore.dado) {//Caminharei pela Esquerda
				if(raizSubArvore.esquerda == null) {
					raizSubArvore.esquerda = no;
				} else {
					insert(no, raizSubArvore.esquerda);
				}
			}
			if(no.dado >= raizSubArvore.dado) {
				if(raizSubArvore.direita == null) {
					raizSubArvore.direita = no;
				} else {
					insert(no, raizSubArvore.direita);
				}
			}
		}
	}
	
	public void insertLeaf(int dado) {
		No no = new No();//Breakpoint para testar o Insert
		no.dado = dado;
		no.esquerda = null;
		no.direita = null;
		insert(no, raiz);
	}
	
	//Realizando os Atravessamentos - Pr�-Fixo (Pr�-Ordem), In-Fixo (Em Ordem) e P�s-Fixo (P�s-Ordem)
	
	//Prefix
	private void prefix(No raizSubArvore) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Prefix
			throw new Exception("�rvore vazia"); 
		} else {
			System.out.print(raizSubArvore.dado);
			System.out.print(" ");
			if(raizSubArvore.esquerda != null) {
				prefix(raizSubArvore.esquerda);
			}
			if(raizSubArvore.direita != null) {
				prefix(raizSubArvore.direita);
			}
		}
	}
	
	//Infix
	private void infix(No raizSubArvore) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Infix
			throw new Exception("�rvore vazia"); 
		} else {
			if(raizSubArvore.esquerda != null) {
				infix(raizSubArvore.esquerda);
			}
			System.out.print(raizSubArvore.dado);
			System.out.print(" ");
			if(raizSubArvore.direita != null) {
				infix(raizSubArvore.direita);
			}
		}
	}
	
	//Postfix
	private void postfix(No raizSubArvore) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Postfix
			throw new Exception("�rvore vazia"); 
		} else {
			if(raizSubArvore.esquerda != null) {
				postfix(raizSubArvore.esquerda);
			}
			if(raizSubArvore.direita != null) {
				postfix(raizSubArvore.direita);
			}
			System.out.print(raizSubArvore.dado);
			System.out.print(" ");
		}
	}
	
	public void prefixSearch() throws Exception {//Estes ser�o p�blicos, pois terei que cham�-los.
		prefix(raiz);	
	}
	
	public void infixSearch() throws Exception {
		infix(raiz);	
	}
	
	public void postfixSearch() throws Exception {
		postfix(raiz);	
	}
	
	//Busca / Search
	private No nodeSearch(No raizSubArvore, int valor) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Search
			throw new Exception("�rvore vazia");
		} else if(valor < raizSubArvore.dado) {
			return nodeSearch(raizSubArvore.esquerda, valor);
		} else if(valor > raizSubArvore.dado) {
			return nodeSearch(raizSubArvore.direita, valor);
		} else {
			return raizSubArvore;
		}
	}
	
	//N�vel da �rvore ele est�!
	private int nodeLevel(No raizSubArvore, int valor) throws Exception {
		if(raiz == null) {//Breakpoint para testar o n�vel / level
			throw new Exception("�rvore vazia");
		} else if(valor < raizSubArvore.dado) {
			return 1 + nodeLevel(raizSubArvore.esquerda, valor);
		} else if(valor > raizSubArvore.dado) {
			return 1 + nodeLevel(raizSubArvore.direita, valor);
		} else {
			return 0;
		}
	}
	
	public void search(int valor) {
		try {
			No no = nodeSearch(raiz, valor);//Breakpoint para testar
			int level = nodeLevel(raiz, valor);
			System.out.println("Dado " + no.dado + " no n�vel " + level);
		} catch (Exception e) {
			System.err.println("Dado n�o encontrado");
		}
	}
	
	private No remove(No raizSubArvore, int valor) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Remove
			throw new Exception("�rvore vazia");
		} else if (valor < raizSubArvore.dado) {
			raizSubArvore.esquerda = remove(raizSubArvore.esquerda, valor);
		} else if (valor > raizSubArvore.dado) {
			raizSubArvore.direita = remove(raizSubArvore.direita, valor);
		} else {
			if(raizSubArvore.esquerda == null && raizSubArvore.direita == null) {//Significa que encontrei uma folha!
				raizSubArvore = null;
			} else if(raizSubArvore.esquerda == null) {//Significa que h� 1 Filho � direita
				raizSubArvore = raizSubArvore.direita;
			} else if(raizSubArvore.direita == null) {//Significa que h� 1 Filho � esquerda
				raizSubArvore = raizSubArvore.esquerda;
			} else {//N� de 2 Filhos
				No no = raizSubArvore.esquerda;
				while(no.direita != null) {
					no = no.direita;
				}
				raizSubArvore.dado = no.dado;
				no.dado = valor;
				raizSubArvore.esquerda = remove(raizSubArvore.esquerda, valor);
			}
		}
		return raizSubArvore;
	}
	
	public void removeChild(int valor) throws Exception {
		remove(raiz, valor);
	}
}

