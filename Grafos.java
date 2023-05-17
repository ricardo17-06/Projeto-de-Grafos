package com.mycompany.grafos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;

/*
Grupo
Ricardo Fialho dos Santos Oliveira RGM: 25966154
Lucas Campos Mateus RGM: 27972640
Luan Mendes RGM: 26469910
Nathanael Santos Moreira  RGM:27666921
*/


public class Grafos{

    public static List<Integer> buscaEmLargura(int[][] grafo, int verticeInicial){
        int n = grafo.length - 1;
        boolean[] visitados = new boolean[n + 1];
        Queue<Integer> fila = new LinkedList<>();
        List<Integer> caminhoPercorrido = new ArrayList<>();

        visitados[verticeInicial] = true;
        fila.add(verticeInicial);

        while(!fila.isEmpty()){
            int verticeAtual = fila.poll();
            caminhoPercorrido.add(verticeAtual); //adiciona o caminho percorido

            for(int i = 1; i <= n; i++){
                if(grafo[verticeAtual][i] > 0 && !visitados[i]){
                    visitados[i] = true;
                    fila.add(i);
                }
            }
        }
        return caminhoPercorrido;
    }

    public static void Gra(int[][] grafo, int vertice, boolean[] visi, List<Integer>componenteAtual){
        visi[vertice] = true;
        componenteAtual.add(vertice);

        for(int i = 0; i < grafo.length; i++){
            if(grafo[vertice][i] == 1 && !visi[i]){
                Gra(grafo, i, visi, componenteAtual);
            }
        }
    }

    public static void main(String[] args){
        int aresta = 0;

        //criação do grafo

        String input = JOptionPane.showInputDialog(null, "Quantos vertices você deseja?");
        int n = Integer.parseInt(input);
        int[][] grafo = new int[n + 1][n + 1];

        //criando os vértices
        
        do{
            input = JOptionPane.showInputDialog(null,"Digite um par de arestas (Neste formato 1 2 // obs:apenas um par por vez)\n e aperte enter para digitar o proximo par, e para encerrar digite -1" );
            if(!input.equals("-1")){
                String[] vertices = input.split(" ");
                int i = Integer.parseInt(vertices[0]);
                int j = Integer.parseInt(vertices[1]);

                if(i < 1 || i > n || j < 1 || j > n){
                    JOptionPane.showMessageDialog(null, "Erro os vertices devem estar entre 1" + n + ".");
                } else{
                    if(i == j){//Adicionando Loops
                        grafo[i][j]++;
                        grafo[j][i]++;
                        aresta++;
                    } else if(grafo[i][j] > 0){//Adicionando paralelas
                        grafo[i][j]++;
                        grafo[j][i]++;
                        aresta++;
                    }else{//Adicionado arestas
                        grafo[i][j] = 1;
                        grafo[j][i] = 1;
                        aresta++;
                    }
                }
            }
        } while(!input.equals("-1"));
        int[] graus = new int[n + 1];

        //calculando o grau dos vertices

        for(int i = 1; i < grafo.length; i++){
            int grau = 0;
            for(int j = 1; j < grafo.length; j++){
                grau += grafo[i][j];
            }
            graus[i] = grau;
        }
        
        //encontra o vertice com maior e menor grau
        int maiorGrau = 0;
        int menorGrau = n;
        for(int i = 1; i < graus.length; i++){
            if(graus[i] > graus[maiorGrau]){
                maiorGrau = i;
            }
            if(graus[i] < graus[menorGrau]){
                menorGrau = i;
            }
        }

        JOptionPane.showMessageDialog(null,"Número de vértices: " + n
                    + "\nNúmero de aresta: " + aresta
                    + "\nVértice " + maiorGrau + " tem o maior grau de conexão, com grau " + graus[maiorGrau]
                    + "\nVértice " + menorGrau + " tem o menor grau de conexão, com grau " + graus[menorGrau]);

        // impressão da matriz de adjacência
        String matriz = "Matriz de adjacência do grafo:\n";
        for(int i = 1; i < grafo.length; i++){
            for(int j = 1; j < grafo.length; j++){
                matriz += grafo[i][j] + " ";
            }
            matriz += "\n";
        }
        JOptionPane.showMessageDialog(null, matriz);

        //Executando a busca em Largura
        input = JOptionPane.showInputDialog(null,"Digite o vértice inicial para a busca em largura:");
        int verticeInicial = Integer.parseInt(input);

        List<Integer> caminhoPercorrido = buscaEmLargura(grafo, verticeInicial);

        StringBuilder caminho = new StringBuilder();
        caminho.append("Caminho percorrido: ");
        for(int vertice : caminhoPercorrido){
            caminho.append(vertice).append(" ");
        }

        JOptionPane.showMessageDialog(null,caminho.toString());

        //componentes conexos

        int vertices = grafo.length;
        boolean[] visi = new boolean[vertices];
        int comp = 0;
        List<List<Integer>> componente = new ArrayList<>();

        for(int i = 1; i < vertices; i++){
            if(!visi[i]){
                comp++;
                List<Integer> componenteAtual = new ArrayList<>();
                Gra(grafo, i, visi, componenteAtual);
                componente.add(componenteAtual);
            }
        }
        if(comp == 0){
            JOptionPane.showMessageDialog(null,"O grafo não possui componentes conexos");
        } else{
            JOptionPane.showMessageDialog(null,"O grafo contém " + comp + " componentes conexos");

            for(int i = 0; i < componente.size(); i++){
                List<Integer> componenteAtual = componente.get(i);
                Collections.sort(componenteAtual);
                String vertice = "Componete " + (i + 1) + ": ";
                for(int j = 0; j < componenteAtual.size(); j++){
                    vertice += componenteAtual.get(j) + " ";
                }
                JOptionPane.showMessageDialog(null,vertice);
            }
        }
    }          
}
