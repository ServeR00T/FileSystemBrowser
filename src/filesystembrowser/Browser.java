package filesystembrowser;

import java.io.File;

/**
 *
 * @author Bassam [@BassamMani]
 * @author Saleem [@ServeR00T]
 */
public class Browser
{
	private LinkNet net = null;
	private int recursionLevel = 0;

	/**
	 * Only called once to initialize the LinkNet and start recursion
	 * @param path 
	 */
	public void browse(String path)
	{
		net = new LinkNet(new File(path));
		browse(path, null);
	}

	/**
	 * Construct the whole LinkNET then print it
	 * @param path		file path as String
	 * @param parent	Link object
	 */
	public void browse(String path, Link parent)
	{
		recursionLevel++;

		File root = new File(path);
		File[] list = root.listFiles();
		for (File f: list)
		{
			if (!f.isHidden())
			{
				if (f.isDirectory())
				{
					// Add the folder to the net and sent it to browse as parent
					browse(f.getAbsolutePath(), net.add(f, parent));
				}
				else
				{
					net.add(f, parent);
				}
			}
		}

		// DO NOT print until the net is fully constructed
		if (recursionLevel == 1)
		{
			net.print();
			System.out.println("\nNumber of Folders: " + net.getNumberOfFolders() + "\tNumber of Files: " + net.getNumberOfFiles());
		}

		recursionLevel--;
	}

	public static void main(String[] args)
	{
		Browser fw = new Browser();
		if (args.length == 0)
		{
			fw.browse("/"); // Be careful about your memory :D
		}
		else
		{
			if (new File(args[0]).isDirectory())
			{
				fw.browse(args[0]);
			}
			else
			{
				System.out.println("You entered invalid directory");
			}
		}
	}
}
