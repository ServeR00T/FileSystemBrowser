package filesystembrowser;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 *
 * @author Bassam [@BassamMani]
 * @author Saleem [@ServeR00T]
 */
public class Link
{
	private Object data;
	private Link next,
		prev,
		firstFolder,	// first child folder
		firstFile,		// first child file
		parent;

	public Link(Object data)
	{
		this.data = data;
	}

	/**
	 * Construct the object and bind it to the LinkNET below its parent
	 * @param data		File object received as Object
	 * @param parent	Link object to bind the Link to it
	 */
	public Link(Object data, Link parent)
	{
		this.data = data;
		this.parent = parent;
		File dataAsFile = (File) data;
		
		if (dataAsFile.isDirectory())
		{
			if (parent.firstFolder == null)
			{
				parent.firstFolder = this;
				this.prev = this;
			}
			else
			{
				this.parent.firstFolder.prev.setNext(this);
				this.parent.firstFolder.setPrev(this);
			}
		}
		else
		{
			if (parent.firstFile == null)
			{
				parent.firstFile = this;
				this.prev = this;
			}
			else
			{
				this.parent.firstFile.prev.setNext(this);
				this.parent.firstFile.setPrev(this);
			}
		}
	}
	
	/**
	 * Get the whole line to be printed
	 * @param		indentation as String
	 * @return		Well formatted line
	 */
	public String getString(String indentation)
	{
		File f = (File) this.data;
		String ret = "";
		ret += getPermissions(f) + "  ";
		ret += getLastModified(f) + "\t";
		ret += getSize(f) + "\t";
		ret += indentation + f.getName();

		return ret;
	}

	/**
	 * Get file or folder permissions
	 * @param f		File object
	 * @return		Traditional Unix permissions as String
	 */
	public static String getPermissions(File f)
	{
		String ret = "";

		ret += (f.isDirectory()) ? "d" : "-";
		ret += (f.canRead()) ? "r" : "-";
		ret += (f.canWrite()) ? "w" : "-";
		ret += (f.canExecute()) ? "x" : "-";

		return ret;
	}

	/**
	 * Get a formatted date by a timestamp
	 * @param f		File object
	 * @return		formatted date as String
	 */
	public static String getLastModified(File f)
	{
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(f.lastModified());
	}

	/**
	 * Get human readable size of the File object
	 * @param f		File object
	 * @return		human readable size as String like: 5M
	 */
	public static String getSize(File f)
	{
		long bytes = f.length();
		int unit = 1024;
		if (bytes < unit)
		{
			return bytes + "B";
		}
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = "KMGTPE".charAt(exp - 1) + "";
		return String.format("%.1f%s", bytes / Math.pow(unit, exp), pre);
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Link getNext()
	{
		return next;
	}

	public void setNext(Link next)
	{
		this.next = next;
	}

	public Link getPrev()
	{
		return prev;
	}

	public void setPrev(Link prev)
	{
		this.prev = prev;
	}

	public Link getFirstFolder()
	{
		return firstFolder;
	}

	public void setFirstFolder(Link firstFolder)
	{
		this.firstFolder = firstFolder;
	}

	public Link getFirstFile()
	{
		return firstFile;
	}

	public void setFirstFile(Link firstFile)
	{
		this.firstFile = firstFile;
	}

	public Link getParent()
	{
		return parent;
	}

	public void setParent(Link parent)
	{
		this.parent = parent;
	}

	@Override
	public String toString()
	{
		return (((File) this.data).getAbsolutePath());
	}
}
