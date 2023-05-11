package com.mycompany.grafos;
import javax.swing.JOptionPane;

public class Grafos {

    public static void main(String[] args) {
        int aresta = 0;
        
        //criando Grafo
        
        String input = JOptionPane.showInputDialog(null, "Quantos vértices você deseja?");
        int n = Integer.parseInt(input);
        int[][] grafo = new int[n + 1][n + 1];
        
        //criando os vértices
        
        do{
            input = JOptionPane.showInputDialog(null, "Digite um par de arestas(neste formato 1 2) e aperte enter para digitar o proximo par, e para encerrar digite -1");
            if(!input.equals("-1")) {
                String[] vertices = input.split(" ");
                int i = Integer.parseInt(vertices[0]);
                int j = Integer.parseInt(vertices[1]);
                
                if(i < 1 || i > n || j < 1 || j > n) {
                    JOptionPane.showMessageDialog(null,"Erro os vértices devem estar entre 1 e " + n + ".");
                } else{
                    if(i == j) { //adicionando loops
                        grafo[i][j]++;
                        grafo[j][i]++;
                        aresta++;
                    } else if(grafo[i][j] > 0){ //adicionando paralelas
                        grafo[i][j]++;
                        grafo[j][i]++;
                        aresta++;
                    } else{ // adicionando arestas
                        grafo[i][j] = 1;
                        grafo[j][i] = 1;
                        aresta++;
                    }
                }
            }
        } while(!input.equals("-1"));
        int[] graus = new int[n + 1];
        
        //caculando o grau dos vertices
        
        for(int i = 1; i < grafo.length; i++){
            int grau = 0;
            for (int j = 1; j < grafo.length; j++){
                grau += grafo[i][j];
            }
            graus[i] = grau;
        }
        
        //encontra o vertice de maior grau e menor grau
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
                    + "\nNúmero de arestas: " + aresta
                    + "\nVértice " + maiorGrau + " tem o maior grau de conexão, com grau " + graus[maiorGrau]
                    + "\nVértice " + menorGrau + " tem o menor grau de conexão, com grau " + graus[menorGrau]);
         
         //impressão da matriz de adjacência
         
         String matriz = "Matriz de adjacência do grafo:\n";
         for(int i = 1; i < grafo.length; i++){
             for(int j = 1; j < grafo.length; j++){
                 matriz += grafo[i][j] + " ";
             }
             matriz += "\n";
         }
         JOptionPane.showMessageDialog(null,matriz);
    }
}
