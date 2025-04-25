import java.io.FileWriter;
public class Write{
    public static void main(String[] args) {
        try{
            FileWriter writee = new FileWriter("Writer.txt");
            writee.write("Uswa Imtiaz\nBSCS-2\nOOPs Practice");
            System.out.println("Done!");
            writee.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}