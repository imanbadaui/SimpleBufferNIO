import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class BufferBuff {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("myNotebook.txt");
		write(path);
		read(path);

	}

	public static void write(Path path) {
		String thoughts = "I think I'm fucking awesome!\n";
		byte[] thoughtsBytesArray = thoughts.getBytes();
		ByteBuffer thoughtsBytesBuffer = ByteBuffer.wrap(thoughtsBytesArray);
		try (FileChannel fileChannelToNoteBook = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
			fileChannelToNoteBook.write(thoughtsBytesBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void read(Path path) {
			ByteBuffer thoughtsBytesBuffer = ByteBuffer.allocate(512);
			try (FileChannel fileChannelToNoteBook = FileChannel.open(path)){
				fileChannelToNoteBook.read(thoughtsBytesBuffer);
				byte[] thoughtsBytesArray = thoughtsBytesBuffer.array();
				String thoughtsString = new String(thoughtsBytesArray).trim();
				System.out.println(thoughtsString);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
