
import java.util.Scanner;
/**
 *
 * @author Pablo Velez
 */
public class Prueba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion;
        String base,exp,n,RUC;
        do{
            System.out.println("1. Calcular la potencia de un numero utilizando una funcion recursiva");
            System.out.println("2. Triangulo de pascal");
            System.out.println("3. RUC persona juridica");
            System.out.println("4. Salir");
            System.out.print("Digite una opcion: ");
            opcion = sc.next();
            while(!opcion.matches("[1-4]{1}")){
                System.out.print("ERROR! Debe ingresar solo un numero de 1 al 4: ");
                opcion = sc.next();
            }
            sc.nextLine();
            switch (Integer.parseInt(opcion)) {
                case 1:
                    System.out.print("Ingrese la base: ");
                    base = sc.next();
                    while(!base.matches("(-|\\+){0,1}[0-9]+(.[0-9]+){0,1}")){
                        System.out.print("ERROR! Debe ingresar solo numeros: ");
                        base = sc.next();
                    }
                    System.out.print("Ingrese el exponente: ");
                    exp = sc.next();
                    while(!exp.matches("(-|\\+){0,1}[0-9]+")){
                        System.out.print("ERROR! Debe ingresar solo numeros: ");
                        exp = sc.next();
                    }
                    System.out.print(base+" elevado a "+exp+" es: ");
                    System.out.println(calcularPotencia(Double.parseDouble(base), Integer.parseInt(exp)));
                    break;
                    
                case 2:
                    System.out.print("Ingrese n: ");
                    n = sc.next();
                    while(!n.matches("[1-9][0-9]*")){
                        System.out.print("Error, ingrese solo numeros y mayores a 0: ");
                        n = sc.next();
                    }
                    trianguloPascal(Integer.parseInt(n));
                    
                    break;
                    
                case 3:
                    System.out.print("Ingrese el ruc juridico a validar: ");
                    RUC = sc.next();
                    if(validarRucJuridico(RUC)){
                        System.out.println("EL RUC ES VALIDO");
                    }else{
                        System.out.println("EL RUC ES INVALIDO");
                    }
                    break;
            }
            
            System.out.println("");
        }while(!opcion.matches("4"));
        
    }
    
    public static double calcularPotencia(double base,int exp){
        if(exp==1){
            return base;
        }
        if(exp==0){
            return 1;
        }
        
        if(exp<0){
            base = 1/base;
            exp = -exp;
        }
        
        return base*(calcularPotencia(base, exp-1));
    }
    
    public static void trianguloPascal(int tamanio){
        int matriz[][] = new int [tamanio][tamanio];
        
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if(j==0){
                    matriz[i][j] = 1;
                }else{
                    if(i>0){
                        matriz[i][j] = matriz[i-1][j-1]+matriz[i-1][j];
                    }
                }
            }
        }
        
        for (int[] is : matriz) {
            for (int i : is) {
                if(i!=0){
                    System.out.printf("%10d",i);
                }    
            }
            System.out.println("");
        }
    }
    
    public static boolean validarRucJuridico(String s){
        boolean valido = s.matches("((0[1-9])|(1[0-9])|(2[0-4]))9[0-9]{7}((00[1-9])|(0[1-9][0-9])|([1-9][0-9][0-9]))");
        
        if(valido==false){
            return valido;
        }
        
        int[] arrayCoef = {4,3,2,7,6,5,4,3,2};
        char[] cedCad = s.toCharArray();
        int digitos[] = new int [cedCad.length];
        int digitoVerificador = Integer.parseInt(String.valueOf(cedCad[9]));
        
        int resultado = 0;
        int mult;
        for (int i = 0; i < arrayCoef.length; i++) {
            mult = arrayCoef[i] * Integer.parseInt(String.valueOf(cedCad[i]));
            resultado+=mult;
        }
        
        int residuo = resultado%11;
        if((residuo==0)&&(digitoVerificador==0)){
            return true;
        }
        
        if((11-residuo)==digitoVerificador){
            return true;
        }
        
        return false;
    }
    
    
    
}
