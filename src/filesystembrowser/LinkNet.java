package filesystembrowser;

import java.io.File;

/**
 *
 * @author Bassam [@BassamMani]
 * @author Saleem [@ServeR00T]
 */
public class LinkNet
{
	private Link root;
	private int numberOfFiles, numberOfFolders;
	String indentationBy = "| ";

	public LinkNet(File root)
	{
		this.root = new Link(root);
	}

	public Link add(File f, Link parent)
	{
		if (parent == null)
		{
			parent = this.root;
		}
		
		Link folderOrFile = new Link(f, parent);
		
		if (f.isDirectory())
		{
			this.numberOfFolders++;
		}
		else
		{
			this.numberOfFiles++;
		}
		
		return folderOrFile;
	}

	public void print()
	{
		print(root, "");
	}

	/**
	 * Print a well formatted line for every Link in the LinkNET
	 * get into the lowest level then start with folders then files
	 * @param root		a Parent to print its children as Link
	 * @param indent	a variable String increase or decrease due to the recursion level
	 */
	public void print(Link root, String indent)
	{
		Link subFolder = root.getFirstFolder();
		Link subFile = root.getFirstFile();

		if (subFolder != null)
		{
			// first print the subfolder formatted line
			System.out.println(subFolder.getString(indent));
			// increase the indentation
			indent += this.indentationBy;
			// do recursion
			print(subFolder, indent);
			// then decrease the indentation
			indent = indent.substring(0, indent.length() - 2);
		}

		// iterate over the subfiles and print it one by one
		while (subFile != null)
		{
			System.out.println(subFile.getString(indent));
			subFile = subFile.getNext();
		}

		// when subfolders and subfiles printed with its children move to the next rootFolder
		if (root != null && root.getNext() != null)
		{
			// decrease the indentation which was used by previous subfolder's children
			indent = indent.substring(0, indent.length() - 2);
			// print the rootFolder formatted line
			System.out.println(root.getNext().getString(indent));
			// increase the indentation to be used by rootFolder's children
			indent += this.indentationBy;
			print(root.getNext(), indent);
		}
	}

	public Link getRoot()
	{
		return root;
	}

	public void setRoot(Link root)
	{
		this.root = root;
	}

	public Link getFolder()
	{
		return root.getFirstFolder();
	}

	public void setFolder(Link folder)
	{
		this.root.setFirstFolder(folder);
	}

	public Link getFile()
	{
		return root.getFirstFile();
	}

	public void setFile(Link file)
	{
		this.root.setFirstFile(file);
	}

	public int getNumberOfFiles()
	{
		return numberOfFiles;
	}

	public void setNumberOfFiles(int numberOfFiles)
	{
		this.numberOfFiles = numberOfFiles;
	}

	public int getNumberOfFolders()
	{
		return numberOfFolders;
	}

	public void setNumberOfFolders(int numberOfFolders)
	{
		this.numberOfFolders = numberOfFolders;
	}

	public String getIndentationBy()
	{
		return indentationBy;
	}

	public void setIndentationBy(String indentationBy)
	{
		this.indentationBy = indentationBy;
	}
}
