import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import test.ArrayTest;

public class Main {

    public static void main(String[] args) throws RunnerException {
	// write your code here
        Options opt = new OptionsBuilder()
                .include(ArrayTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .measurementIterations(1)
                .build();

        new Runner(opt).run();

       /* opt = new OptionsBuilder()
                .include(LinkedTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .measurementIterations(1)
                .build();
        new Runner(opt).run();*/
    }
}
