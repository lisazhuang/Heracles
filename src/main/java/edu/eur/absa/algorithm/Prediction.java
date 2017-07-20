package edu.eur.absa.algorithm;

import java.util.Collection;
import java.util.HashSet;

import edu.eur.absa.model.DataEntity;
import edu.eur.absa.model.Annotations;
import edu.eur.absa.model.Dataset;
import edu.eur.absa.model.Span;

/**
 * Prediction can be an annotation-type prediction, which is bound to a given Span,
 * or it can be the prediction of a Span itself. Also, it might be both.
 * To predict a span you need the textualUnit-spanId + spanType + order of first word + order of last word
 * 
 * @author kim
 *
 */
public class Prediction {

	//the predicted annotations
	private Annotations annotations;
	//the given annotatable for which annotations are predicted, is null when annotations are for predicted span
	private DataEntity annotatable;
	private Dataset dataset;
	
	//when doing a span prediction, these are the necessary data
	private Span parentSpan;
	private String spanType;
	private int firstWordOrder;
	private int lastWordOrder;
		
	public Prediction(DataEntity annotatable){
		this.annotatable = annotatable;
		this.dataset = annotatable.getDataset();
		this.annotations = new Annotations(dataset);
	}
	
	public Prediction(Span parentSpan, String spanType, int firstWordOrder, int lastWordOrder){
		this.parentSpan = parentSpan;
		this.spanType = spanType;
		this.firstWordOrder = firstWordOrder;
		this.lastWordOrder = lastWordOrder;
		this.dataset = parentSpan.getDataset();
		this.annotations = new Annotations(dataset);
	}

	public Annotations getAnnotations() {
		return annotations;
	}
	
	public DataEntity getAnnotatable(){
		return annotatable;
	}
	
	public String getSpanType(){
		return spanType;
	}
	public Span getParentSpan(){
		return parentSpan;
	}
	public int getOrderOfFirstWord(){
		return firstWordOrder;
	}
	public int getOrderOfLastWord(){
		return lastWordOrder;
	}
	
	/**
	 * Evaluators expect a Set of Predictions, but often there is just one
	 * This method simply wraps this Prediction object into a HashSet for convenience
	 * @return A HashSet containing just this Prediction
	 */
	public HashSet<Prediction> getSingletonSet(){
		HashSet<Prediction> singletonSet = new HashSet<>();
		singletonSet.add(this);
		return singletonSet;
	}

	public Dataset getDataset() {
		return dataset;
	}
	
}