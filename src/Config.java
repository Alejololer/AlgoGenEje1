import org.jgap.Chromosome;
import org.jgap.Configuration;
    import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.IChromosome;


public class Config {
    public void configurarAG(int población, int longitud, int numero_evoluciones, int numero_iteraciones) throws InvalidConfigurationException{
        Configuration conf = new DefaultConfiguration();
        FuncionAptitud fa = new FuncionAptitud(longitud);
        try {
            conf.setFitnessFunction(fa);
            conf.setPopulationSize(población);
            IChromosome c = new Chromosome(conf, ejemploChromosome(conf, población));
            conf.setSampleChromosome(c);
            Genotype poblaciGenotype = Genotype.randomInitialGenotype(conf);
            evolucionar(poblaciGenotype, numero_evoluciones, numero_iteraciones, longitud);
        } catch (InvalidConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Gene[] ejemploChromosome(Configuration conf, int longitud){
        Gene[] ejemplo = new Gene[longitud];
        for (int i = 0; i< longitud; i++){
            try {
                ejemplo[i] = new IntegerGene(conf, 0, 1);
            } catch (InvalidConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ejemplo;
    }

    private void evolucionar(Genotype población, int número_evoluciones, int número_iteraciones, int longitud){
        for (int i=0; i < número_iteraciones; i++){
            población.evolve(número_evoluciones);
            población.getFittestChromosome(); //ón.getFitt(número_iteraciones); //
            ImprimirMejores(población.getChromosomes(), longitud);
            System.out.println("--------------------------------");
            ImprimirMejor(población.getFittestChromosome(), longitud);
            System.out.println("--------------------------------");
        }
    }

    private void ImprimirMejores(IChromosome[] ic, int longitud){
        for (IChromosome iChromosome : ic) {
            ImprimirMejor(iChromosome, longitud);
        }
    }

    private void ImprimirMejor(IChromosome ic, int longitud) {
        String x ="";
        String d = "";
        for (int i = 1; i < longitud; i++){
            if(i > longitud/2){
                d = d + (Integer) ic.getGene(i).getAllele();
            }
            else
                x = x + (Integer) ic.getGene(i).getAllele();
        }
        int v0 = (int) ic.getGene(0).getAllele();
        int vx = Integer.parseInt(x, 2);
        double vd = Integer.parseInt(d, 2);
        double valor = vx + vd;

        if(v0 == 0){
            valor= -valor;
        }
        System.out.println("Valor de x: " + valor);
        System.out.println("    Valor de la función: "+ Math.log10(Math.sqrt(Math.pow(valor, 4))));
    }
}
