public class Bucles2 {

    public static void main(String[] args){

        int vueltas = 0;
        String[] tallas = {"S", "M", "L", "XL"};

        for (int i = 1; i<=20; i++){
            if (i%2 == 0){
                System.out.println(i);
            }
        }

        System.out.println();

        for (int i = 0; i <= 3; i++){
            System.out.println((i+1)+" - "+tallas[i]);
        }

        System.out.println();

        int numero = 1;

        for (String talla : tallas) {
            System.out.println(numero + " - " + talla);
            numero++;
        }
    }
}
