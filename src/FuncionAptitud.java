import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class FuncionAptitud extends FitnessFunction{

    private int longitud;

    
    public FuncionAptitud(int longitud) {
        this.longitud = longitud;
    }


    @Override
    protected double evaluate(IChromosome ic) {
        String x ="";
        String d = "";
        for (int i = 1; i < longitud; i++){
            if(i > longitud/2){
                d = d + (Integer) ic.getGene(i).getAllele();
            }
            x = x + (Integer) ic.getGene(i).getAllele();
        }
        int v0 = (int) ic.getGene(0).getAllele();
        int vx = Integer.parseInt(x, 2);
        double vd = Integer.parseInt(d, 2);
        double valor = vx + vd;

        if(v0 == 0){
            valor= -valor;
        }

        int valMaxGen = (int) ( Math.pow(2, (longitud-1)/2)+(1/Math.pow(2, (longitud-1))/2)-Math.pow(2, -1)); //Para el máximo ya no me acordaba esta formula para las cadenas de bits lel
        double valMax = Math.log10(Math.sqrt(Math.pow(valMaxGen, 4)));
        double score = valMax - Math.log10(Math.sqrt(Math.pow(valor, 4)));
        if (score < 0)
            score = 0;
        return score;
    }
    
}
