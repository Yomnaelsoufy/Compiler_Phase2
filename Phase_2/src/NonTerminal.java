import java.util.ArrayList;

public class NonTerminal extends Component{

    boolean epsilon_production ;

    ArrayList< ArrayList<Component> > productions ;

    ArrayList<Terminal> first_set ;

    ArrayList<Terminal> folllow_set ;

    public NonTerminal(String name) {
        super(name);
        productions = new ArrayList<>();
        first_set = new ArrayList<>();
        folllow_set = new ArrayList<>();
        epsilon_production = false ;
    }


}
