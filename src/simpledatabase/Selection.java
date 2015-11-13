package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	Operator newTable;
	private Tuple tuple;
	private boolean isReturn;
	

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.from = whereTablePredicate;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();
	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		tuple = child.next();
		isReturn = false;
		newTable = child;
		
		if(child.from.equals(whereTablePredicate)){
			//System.out.println("Hello, I am " + ((Table) child).returnFromName());
			while(tuple != null && !isReturn){
				for(int i = 0; i < tuple.getAttributeList().size(); i++){
		    		if(tuple.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate)
		    				&& tuple.getAttributeList().get(i).getAttributeValue().equals(whereValuePredicate)){
		    			isReturn = true;
		    			return new Tuple(tuple.getAttributeList());
		    		}
		    	}
				if(!isReturn)
					tuple = child.next();
			}
		}else{
			if(tuple != null){
					return new Tuple(tuple.getAttributeList());
			}
		}
		
		return null;
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return(child.getAttributeList());
	}

	
}