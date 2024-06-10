package br.edu.fateczl.arvore.inteiro.ArvoreInt;

public class Arvore {
	
	No raiz;
	
	//Inicializando a Árvore
	public Arvore() {
		raiz = null;
	}
	
	//Insert - Inserção
	
	//Pega o valor da raiz e verificamos se é um valor maior ou menor que a raiz, para sabermos
	//se faremos inserção à direita (se maior) ou à esquerda (se menor)!
	//Tem que testar se há algum No à direita ou à esquerda. E, havendo, saber se é maior ou menor que a raiz.
	//Método de verificação, recursivamente. E, a princípio, temos que usar o private.
	private void insert(No no, No raizSubArvore) {
		if(raiz == null) {//Se for nula, meu No é a raiz! //Breakpoint para testar o Insert
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
	
	//Realizando os Atravessamentos - Pré-Fixo (Pré-Ordem), In-Fixo (Em Ordem) e Pós-Fixo (Pós-Ordem)
	
	//Prefix
	private void prefix(No raizSubArvore) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Prefix
			throw new Exception("Árvore vazia"); 
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
			throw new Exception("Árvore vazia"); 
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
			throw new Exception("Árvore vazia"); 
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
	
	public void prefixSearch() throws Exception {//Estes serão públicos, pois terei que chamá-los.
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
			throw new Exception("Árvore vazia");
		} else if(valor < raizSubArvore.dado) {
			return nodeSearch(raizSubArvore.esquerda, valor);
		} else if(valor > raizSubArvore.dado) {
			return nodeSearch(raizSubArvore.direita, valor);
		} else {
			return raizSubArvore;
		}
	}
	
	//Nível da Árvore ele está!
	private int nodeLevel(No raizSubArvore, int valor) throws Exception {
		if(raiz == null) {//Breakpoint para testar o nível / level
			throw new Exception("Árvore vazia");
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
			System.out.println("Dado " + no.dado + " no nível " + level);
		} catch (Exception e) {
			System.err.println("Dado não encontrado");
		}
	}
	
	private No remove(No raizSubArvore, int valor) throws Exception {
		if(raiz == null) {//Breakpoint para testar o Remove
			throw new Exception("Árvore vazia");
		} else if (valor < raizSubArvore.dado) {
			raizSubArvore.esquerda = remove(raizSubArvore.esquerda, valor);
		} else if (valor > raizSubArvore.dado) {
			raizSubArvore.direita = remove(raizSubArvore.direita, valor);
		} else {
			if(raizSubArvore.esquerda == null && raizSubArvore.direita == null) {//Significa que encontrei uma folha!
				raizSubArvore = null;
			} else if(raizSubArvore.esquerda == null) {//Significa que há 1 Filho à direita
				raizSubArvore = raizSubArvore.direita;
			} else if(raizSubArvore.direita == null) {//Significa que há 1 Filho à esquerda
				raizSubArvore = raizSubArvore.esquerda;
			} else {//Nó de 2 Filhos
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

