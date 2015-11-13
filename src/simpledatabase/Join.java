package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	private Tuple tuple, tuple2;
	private Boolean isJoin;
	private Operator joinOperator, joinedOperator;
	ArrayList<Tuple> tuples1;
	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
		if(leftChild.from.equals(joinPredicate)){
			joinOperator = leftChild;
			joinedOperator = rightChild;
		}else{
			joinOperator = rightChild;
			joinedOperator = leftChild;
		}
		
		tuple2 = joinOperator.next();
		while(tuple2 != null){
			tuples1.add(tuple2);
			tuple2 = joinOperator.next();
		}
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		isJoin = false;
		tuple = joinedOperator.next();
		
		while(tuple != null && !isJoin){
			//System.out.println("\n new iteration;");
			//printTuple(tuple);
			isJoin = false;
			newAttributeList = new ArrayList<Attribute>();
			for(int i = 0; i < tuple.attributeList.size(); i++){
				if(tuple.getAttributeList().get(i).getAttributeName().equals("id")){
					for(Tuple t: tuples1){
						for(int j = 0; j < t.getAttributeList().size(); j++){
							if(t.getAttributeName(j).equals("id") && t.getAttributeValue(j).equals(tuple.getAttributeList().get(i).getAttributeValue())){
								newAttributeList.addAll(t.getAttributeList());
								isJoin = true;
							}
						}
					}
				}else{
					newAttributeList.add(tuple.getAttributeList().get(i));
				}
			}
			if(isJoin){
				return new Tuple(newAttributeList);
			}else{
				tuple = joinedOperator.next();
			}
		}
		return null;
	}
	
	
	public void printTuple(Tuple printTuple){
		for(int i = 0; i < printTuple.attributeList.size(); i++){
			if(printTuple.getAttributeName(i).equals("id")){
				System.out.print("Attribute " + (i+1) +": ");
				System.out.print("Attribute name: " +  printTuple.getAttributeName(i) + " / ");
				System.out.print("Attribute type: " + printTuple.getAttributeType(i) + " / ");
				System.out.println("Attribute value: " + printTuple.getAttributeValue(i));
			}
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}