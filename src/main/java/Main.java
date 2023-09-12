import java.util.ArrayList;
import java.util.List;

public class Main {

    // Change here to adapt the pick count
    private static int TERRITORY_COUNT = 3;


    private static void calculatePickCombinations(Node parentNode) {
        if (parentNode.getAssignedPicks().size() == TERRITORY_COUNT) {
            return;
        }
        boolean firstStillPossible = parentNode.isFirstPossible();
        boolean secondStillPossible = parentNode.isSecondPossible();
        int currentDepth = parentNode.getAssignedPicks().size() + 1;

        int ownPicks = currentDepth - 1;
        if (firstStillPossible) {
            // we are moving first
            int opponentPicks = currentDepth % 2 == 1 ? currentDepth - 1 : currentDepth;
            int maxPickNumber = opponentPicks + 1 + ownPicks;
            int highestCurrentPick = 0;
            if (currentDepth != 1) {
                highestCurrentPick = parentNode.getAssignedPicks().get(parentNode.getAssignedPicks().size() - 1);
            }

            for (int i = highestCurrentPick + 1; i <= maxPickNumber; i++) {
                Node childNode = parentNode.getCopy();
                childNode.setSecondPossible(false);
                childNode.getAssignedPicks().add(i);
                parentNode.getChildNodes().add(childNode);
                calculatePickCombinations(childNode);
            }
        }

        if (secondStillPossible) {
            // we are moving second
            int opponentPicks = currentDepth % 2 == 1 ? currentDepth : currentDepth - 1;
            int maxPickNumber = opponentPicks + 1 + ownPicks;
            int highestCurrentPick = 0;
            if (currentDepth != 1) {
                highestCurrentPick = parentNode.getAssignedPicks().get(parentNode.getAssignedPicks().size() - 1);
            }
            for (int i = highestCurrentPick + 1; i <= maxPickNumber; i++) {
                Node childNode = parentNode.getCopy();
                childNode.setFirstPossible(false);
                childNode.getAssignedPicks().add(i);
                parentNode.getChildNodes().add(childNode);
                calculatePickCombinations(childNode);
            }
        }

    }


    private static void calculatePickCombinations() {
        Node root = new Node();
        calculatePickCombinations(root);
        print(root);
    }

    private static void print(Node root) {
        List<Node> leafNodes = getAllLeaves(root);
        for (int i = 0; i < leafNodes.size(); i++) {
            Node printNode = leafNodes.get(i).getCopy();
            for (int j = i + 1; j < leafNodes.size(); j++) {
                if (leafNodes.get(i).getAssignedPicks().equals(leafNodes.get(j).getAssignedPicks())) {
                    leafNodes.remove(j);
                    printNode.setFirstPossible(true);
                    printNode.setSecondPossible(true);
                }
            }
            System.out.println(printNode);
        }


    }

    private static List<Node> getAllLeaves(Node root) {
        List<Node> leaves = new ArrayList<>();


        if (root == null) {
            return leaves;
        }

        if (root.getChildNodes().isEmpty()) {
            leaves.add(root);
        } else {
            for (Node child : root.getChildNodes()) {
                leaves.addAll(getAllLeaves(child));
            }
        }

        return leaves;
    }


    public static void main(String[] args) {
        calculatePickCombinations();
    }

}
