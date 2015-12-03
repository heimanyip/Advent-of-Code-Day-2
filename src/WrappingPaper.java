import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WrappingPaper {
	public static int total = 0;
	public static int totalRibbon = 0;

	public static void main(String[] args) throws IOException {
		String fileName = System.getProperty("user.dir") + "/input.txt";
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			lines.forEachOrdered(line -> calculate(line));
		} catch (IOException ex) {
			System.out.printf("Error: %s\n.", ex);
		}

		System.out.printf("Total wrapping paper required: %d square feet.\n", total);
		System.out.printf("Total ribbon required: %d feet.\n", totalRibbon);
	}

	public static void calculate(String input) {
		surfaceArea(input);
		getRibbonAmount(input);
	}

	public static void surfaceArea(String input) {
		String[] dimensionAsString = input.split("x");
		int[] dimension = new int[3];
		for (int i = 0; i < dimensionAsString.length; i++) {
			dimension[i] = Integer.parseInt(dimensionAsString[i]);
		}
		int surfaceArea = (2 * dimension[0] * dimension[1]) + (2 * dimension[1] * dimension[2])
				+ (2 * dimension[2] * dimension[0])
				+ Math.min(Math.min(dimension[0] * dimension[1], (dimension[1] * dimension[2])),
						(dimension[2] * dimension[0]));
		total += surfaceArea;

	}

	public static void getRibbonAmount(String input) {
		String[] dimensionAsString = input.split("x");
		int[] dimension = new int[3];
		for (int i = 0; i < dimensionAsString.length; i++) {
			dimension[i] = Integer.parseInt(dimensionAsString[i]);
		}

		int bow = dimension[0] * dimension[1] * dimension[2];
		int wrap = Math.min(
				Math.min(((2 * dimension[0]) + (2 * dimension[1])), (2 * dimension[2]) + (2 * dimension[1])),
				((2 * dimension[0]) + (2 * dimension[2])));
		totalRibbon += (bow + wrap);
	}

}