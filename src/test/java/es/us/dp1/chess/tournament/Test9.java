package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import es.us.dp1.chess.tournament.horsemovevalidator.HorseMoveValidatorTest;
import es.us.dp1.chess.tournament.match.horsemovevalidator.AlmostValidHorseMoveValidator;
import es.us.dp1.chess.tournament.match.horsemovevalidator.CrappyHorseMoveValidator;
import es.us.dp1.chess.tournament.match.horsemovevalidator.DummyHorseMoveValidator;
import es.us.dp1.chess.tournament.match.horsemovevalidator.HorseMoveValidator;
import es.us.dp1.chess.tournament.match.horsemovevalidator.StrangeMovesValidator;
import es.us.dp1.chess.tournament.match.horsemovevalidator.ValidHorseMoveValidator;
import junit.framework.AssertionFailedError;

public class Test9 extends ReflexiveTest{    
    public class WrapperAlgorithm implements HorseMoveValidator {
        private HorseMoveValidator algorithm;
        private int numRuns;

        public WrapperAlgorithm(HorseMoveValidator algorithm) {
            this.algorithm = algorithm;
            this.numRuns = 0;
        }        

        public int getNumRuns() {
            return numRuns;
        }

        @Override
        public boolean isValid(int originX, int originY, int destinationX, int destinationY) {
            numRuns++;
            return algorithm.isValid(originX, originY, destinationX, destinationY);
        }

    }



    @ParameterizedTest    
    @MethodSource("provideAlgorithmsAndExpectedResults")
    public void testHorseMoveValidatorAlgorithm(HorseMoveValidator alg, boolean shouldFail){
        // Configure SUT:
        HorseMoveValidatorTest cdaTest=new HorseMoveValidatorTest();
        WrapperAlgorithm wrapper = new WrapperAlgorithm(alg);
        cdaTest.setAlgorithm(wrapper);
        int numberOfExecutedTestMethods=0;
        // ExecuteTests        
        numberOfExecutedTestMethods=executeTests(cdaTest, shouldFail);             
        if(numberOfExecutedTestMethods<1)  
            fail("You have not specified any test method!");    
        if(wrapper.getNumRuns() < 1)
            fail("The SUT has not been executed in the test!");    
    }

    private void executeAfterEach(HorseMoveValidatorTest cdaTest) {
        Method[] methods=cdaTest.getClass().getDeclaredMethods();
        for(Method method:methods){
            if(isMethodAnnotatedWithAfterEach(method)){
                try {                    
                    method.invoke(cdaTest);                    
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error while trying to invoke method:"+method.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    private int executeTests(HorseMoveValidatorTest cdaTest, boolean shouldFail) {
        int executed=0;
        Method[] methods=cdaTest.getClass().getDeclaredMethods();
        boolean failDetected=false;
        String message="No test method detected the faulty implementation of the algorithm";
        for(Method method:methods){
            if(isMethodAnnotatedWithTest(method)){
                try {                                        
                    executed++;
                    executeBeforeEach(cdaTest);
                    method.invoke(cdaTest);     
                    executeAfterEach(cdaTest);
                }catch(AssertionError assertionError){
                    failDetected=true;
                    message="The test method named "+method.getName()+" failed (and should not)! AsssertionError: "+assertionError.getMessage();
                } catch(InvocationTargetException e){
                    if(e.getTargetException() instanceof org.opentest4j.AssertionFailedError){
                        failDetected=true;
                        message="The test method named "+method.getName()+" failed (and should not)! AsssertionError: "
                                    +((org.opentest4j.AssertionFailedError)e.getTargetException()).getMessage();
                    }else
                        System.out.println("Error while trying to invoke method:"+method.getName());                    
                }catch (IllegalAccessException | IllegalArgumentException  e) {                    
                    System.out.println("Error while trying to invoke method:"+method.getName());                    
                }
            }
        }
        if(failDetected!=shouldFail)
            fail(message);
        return executed;
    }

    private void executeBeforeEach(HorseMoveValidatorTest cdaTest) {
        Method[] methods=cdaTest.getClass().getDeclaredMethods();
        for(Method method:methods){
            if(isMethodAnnotatedWithBeforeEach(method)){
                try {
                    method.invoke(cdaTest);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error while trying to invoke method:"+method.getName());
                    e.printStackTrace();
                }
            }
        }
    }    

    public static Stream<Arguments> provideAlgorithmsAndExpectedResults(){
        return Stream.of(
            Arguments.of(new ValidHorseMoveValidator(), false),
            Arguments.of(new DummyHorseMoveValidator(), true),
            Arguments.of(new CrappyHorseMoveValidator(), true),
            Arguments.of(new StrangeMovesValidator(), true),
            Arguments.of(new AlmostValidHorseMoveValidator(), true)            
        );
    }
        
}
