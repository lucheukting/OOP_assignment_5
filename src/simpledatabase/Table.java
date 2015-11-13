package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private Tuple tuple;
	private int index = 0;
	private String line, attributeLine, dataTypeLine;
	private ArrayList<String> storeRecord = new ArrayList<String>();

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			while ((line = br.readLine()) != null) {
				if(this.index == 0){
					attributeLine = line;
				}else if(this.index == 1){
					dataTypeLine = line;
				}else{
					storeRecord.add(line);
				}
				this.index++;
			}
			this.index = 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
	    if(this.index < storeRecord.size()){
	    	this.tuple = new Tuple(attributeLine, dataTypeLine, storeRecord.get(index));
	    	tuple.setAttributeName();
	    	tuple.setAttributeType();
	    	tuple.setAttributeValue();
	    	this.index++;
	    	return this.tuple;
	    }
	    this.index = 0;
	    return null;	
		
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}