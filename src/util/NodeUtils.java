package util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.prop4j.Literal;
import org.prop4j.Node;

public class NodeUtils {

    private NodeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Node> getHornClauses(Node[] clauses) {
        List<Node> hornClauses = new LinkedList<>();

        for (Node clause : clauses) {
            List<Literal> literals = clause.getLiterals();

            boolean containsPositive = false;
            boolean isHorn = true;

            for (Literal literal : literals) {
                if (literal.positive) {
                    if (!containsPositive)
                        containsPositive = true;
                    else {
                        isHorn = false;
                        break;
                    }
                }
            }

            if (isHorn)
                hornClauses.add(clause);
        }

        return hornClauses;
    }

	public static Double getVariableCount(Object variable, Node[] clauses) {
		double result = 0;

		for (Node clause : clauses)
			result += Collections.frequency(clause.getVariables(), variable);
			
		return result;
	}

    public static Double getCountOfPostiveLiterals(Node clause) {
        return getCountOfPostiveLiterals(clause.getLiterals());
    }

    public static Double getCountOfPostiveLiterals(List<Literal> literals) {
        AtomicInteger result = new AtomicInteger();

        literals.forEach(e -> {
                            if(e.positive) { 
                                result.getAndIncrement();
                            }
                        });
    
        return result.doubleValue();
    }

    public static List<Double> getDegreeStatistics(Node[] nodes) {
        List<Double> ratio = new LinkedList<>();

        for (Node node : nodes) {
            Double outDegree = (double) node.getChildren().length;
            Double sumDegree = outDegree + 1;

            ratio.add(outDegree / sumDegree);
        }
        return ratio;
    }

}
