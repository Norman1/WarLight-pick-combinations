import java.util.ArrayList;
import java.util.List;

public class Node {


    private List<Node> childNodes = new ArrayList<>();
    private List<Integer> assignedPicks = new ArrayList<>();
    private boolean firstPossible = true;
    private boolean secondPossible = true;


    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public List<Integer> getAssignedPicks() {
        return assignedPicks;
    }

    public void setAssignedPicks(List<Integer> assignedPicks) {
        this.assignedPicks = assignedPicks;
    }

    public boolean isFirstPossible() {
        return firstPossible;
    }

    public void setFirstPossible(boolean firstPossible) {
        this.firstPossible = firstPossible;
    }

    public boolean isSecondPossible() {
        return secondPossible;
    }

    public void setSecondPossible(boolean secondPossible) {
        this.secondPossible = secondPossible;
    }

    public Node getCopy() {
        Node copy = new Node();
        copy.setFirstPossible(isFirstPossible());
        copy.setSecondPossible(isSecondPossible());
        copy.setChildNodes(new ArrayList<>()); // we don't want to copy that I guess
        copy.setAssignedPicks(new ArrayList<>(getAssignedPicks()));
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(assignedPicks).toString();
        sb.append(" (");
        if (firstPossible) {
            sb.append("first");
        }
        if (firstPossible && secondPossible) {
            sb.append(", ");
        }
        if (secondPossible) {
            sb.append("second");
        }
        sb.append(")");

        return sb.toString();
    }


}
