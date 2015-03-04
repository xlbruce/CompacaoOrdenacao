package br.com.gilson;

/**
 *
 * @author Gilson da Silva
 * @version 1.0
 */
public class Comparacao {

    static int[] k10, k50, k100, k500, k1000, tmp;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initVetores("pior");
        test(k10);

    }

    public static void test(int[] v) {
        System.out.println("Tamanho: " + v.length);

        //Testa mergeSort
        tmp = v.clone();
        long inicio = System.currentTimeMillis(), fim, tempoGasto;
        mergesort(tmp, 0, tmp.length);
        fim = System.currentTimeMillis();
        tempoGasto = fim - inicio;
        System.out.println("Merge: " + tempoGasto);
        
        //Testa Quick Sort
         tmp = v.clone();
         inicio = System.currentTimeMillis();
         quickSort(tmp, 0, tmp.length - 1);
         fim = System.currentTimeMillis();
         tempoGasto = fim - inicio;
         System.out.println("Quick: " + tempoGasto);        

         //Testa Insertion Sort
         tmp = v.clone();
         inicio = System.currentTimeMillis();
         insertionSort(v);
         fim = System.currentTimeMillis();
         tempoGasto = fim - inicio;
         System.out.println("Insertion: " + tempoGasto);
         
         //Testa Bubble Sort
         tmp = v.clone();
         inicio = System.currentTimeMillis();
         bubbleSort(tmp, tmp.length);
         fim = System.currentTimeMillis();
         tempoGasto = fim - inicio;
         System.out.println("Bubble: " + tempoGasto);

    }

    /**
     *
     * @param size Tamanho do vetor
     * @param s Passando a String "ruim" como parâmetro, o vetor é inicializado
     * para comparação em pior caso.
     * @return Um vetor preenchido
     */
    static int[] initVetor(int size, String s) {
        if (!s.equalsIgnoreCase("pior")) {
            return initVetor(size);
        } else {
            //Inicializa um vetor para comparação no pior caso
            int[] v = new int[size];
            v = new int[size];
            for (int i = v.length - 1; i > 0; i--) {
                v[i] = v.length - i;
            }
            return v;
        }
    }

    static int[] initVetor(int size) {
        //Inicializa vetores para comparação caso médio
        int[] v = new int[size];
        for (int i = 0; i < v.length; i++) {
            v[i] = (int) (Math.random() * 100000);
        }
        return v;
    }

    static void initVetores() {
        k10 = initVetor(10000);
        k50 = initVetor(50000);
        k100 = initVetor(100000);
        k500 = initVetor(500000);
        k1000 = initVetor(1000000);
    }

    static void initVetores(String s) {
        if (!s.equalsIgnoreCase("pior")) {
            initVetores();
        } else {
            k10 = initVetor(10000, "pior");
            k50 = initVetor(50000, "pior");
            k100 = initVetor(100000, "pior");
            k500 = initVetor(500000, "pior");
            k1000 = initVetor(1000000, "pior");
        }

    }

    public static void mergesort(int[] data, int first, int n) {
        int n1; // Tamanho da primeira metade do vetor
        int n2; // Tamanho da segunda metade do vetor
        if (n > 1) {
// Calcula o tamanho das duas metades
            n1 = n / 2;
            n2 = n - n1;
// ordena data[first] até data[first+n1-1]
            mergesort(data, first, n1);
// ordena data[first+n1] até o final
            mergesort(data, first + n1, n2);
// mescla as duas metades ordenadas
            merge(data, first, n1, n2);
        }
    }

    private static void merge(int[] data, int first, int n1, int n2) {
        int[] temp = new int[n1 + n2]; // Cria o vetor temporário
// Nro de elementos copiados do vetor data para o vetor temp
        int copied = 0;
// Nro de elementos copiados da primeira metade do vetor data
        int copied1 = 0;
// Nro de elementos copiados da segunda metade do vetor data
        int copied2 = 0;
// Índice p/ copiar elementos do vetor temp p/ o vetor data
        int i;
// Mescla os elementos, copiando das duas metades do vetor
// data para o vetor temporário.
        while ((copied1 < n1) && (copied2 < n2)) {
            if (data[first + copied1] < data[first + n1 + copied2]) {
                temp[copied++] = data[first + (copied1++)];
            } else {
                temp[copied++] = data[first + n1 + (copied2++)];
            }
        }
    }

    public static int separa(int[] vetor, int ini, int fim) {
        int pivo, esq;
        pivo = vetor[ini];
        esq = 0;
        while (ini < fim) {
            if (esq == 0) {
                if (pivo >= vetor[fim]) {
                    vetor[ini] = vetor[fim];
                    ini++;
                    esq = 1;
                } else {
                    fim--;
                }
            } else if (pivo < vetor[ini]) {
                vetor[fim] = vetor[ini];
                fim--;
                esq = 0;
            } else {
                ini++;
            }
        }
        vetor[fim] = pivo;
        return fim;
    }

    public static void quickSort(int[] vetor, int ini, int fim) {
        int k;
        if (fim > ini) {
            k = separa(vetor, ini, fim);
            quickSort(vetor, ini, k - 1);
            quickSort(vetor, k + 1, fim);
        }
    }

    public static void insertionSort(int[] vetor) {
        int j, aux;
        for (int i = 1; i < vetor.length; i++) {
            aux = vetor[i];
            for (j = i - 1; j >= 0 && aux < vetor[j]; j--) {
                vetor[j + 1] = vetor[j];
            }
            vetor[j + 1] = aux;
        }
    }

    public static void bubbleSort(int[] chave, int n) {
        int temp;
        // inicia o Bubble Sort
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (chave[j - 1] > chave[j]) {
                    //troca os elementos
                    temp = chave[j - 1];
                    chave[j - 1] = chave[j];
                    chave[j] = temp;
                }
            }
        }
    }

}
