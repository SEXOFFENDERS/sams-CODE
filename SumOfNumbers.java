
public class SumOfNumbers {

	public String getSum(int start, int finish) {
		
		boolean Start_End = true;
		start= start -1;
		int end= 0;
		String result = "";
		if (finish+1 <= start){
			return "";
		}
		
		if (Start_End){
			while (Start_End) {
				end = end +start+1;
				start = start +1;
				result= result + start;
				Start_End = start <= finish-1;
				
				if (Start_End) {
					
					result= result + " + ";
				}
					else {
						result = result + " =";
					}
				}
			}
		System.out.println(result + " " + end);
		return result + " " + end;
	}

}
