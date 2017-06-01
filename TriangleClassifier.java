
public class TriangleClassifier {

	public String getClassification(int angleA, int angleB, int angleC)
	{
		String first = "";
		String second = "";
		
		if (angleA + angleB +angleC != 180) {
			return "INVAILD";
			} 
		if (angleA==0 || angleB==0 || angleC==0) {
			return "INVALID";
		}
		if (angleA==60 && angleB==60 && angleC==60) {
			
			return "equilateral";
		}
		if (angleA==90 || angleB==90 || angleC==90 ) {
			first = "right";
			}
		else if (angleA<=90 && angleB<=90 && angleC<=90) {
			first = "acute";
		}
		else {
			first = "obtuse";
		}
		if (angleA != angleB && angleA != angleC && angleB != angleC){
			second = "scalene";
		}
		else {
				second = "isosceles";
		}
		return first + " " + second;
	}

}
