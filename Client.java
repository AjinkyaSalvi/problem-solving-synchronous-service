import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	public static void main(String args[]) throws Exception
	{
		// Request connection to server
		Socket s = new Socket("127.0.0.1", 1422);

		// Create text output and input streams
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());

		Scanner t = new Scanner(System.in);

		int n, i, j;
		String c, msgin, msgout;

		// 01. Receive connection message
		msgin = dis.readUTF();
		System.out.println(msgin);

		while(true)
		{
			System.out.println("\nTo select an operation:\n\n01.\tEnter '1' to calculate the value of Pi,\n02.\tEnter '2' to add two numbers,\n03.\tEnter '3' to sort an array,\n04.\tEnter '4' to multiply three matrices and\n05.\tEnter '0' to EXIT.\n\nEnter your choice:");
			c = t.nextLine();

			// 02. Send choice
			dos.writeUTF(c);
			dos.flush();

			if(c.equals("1"))
			{
				// calculate_pi()

				// 03. Receive value of Pi
				msgin = dis.readUTF();
				System.out.println(msgin);
			}

			if(c.equals("2"))
			{
				// add(i, j)

				// 04. Send two numbers
				System.out.println("\nEnter the value of two numbers:");
				msgout=t.nextLine();
				dos.writeUTF(msgout);
				dos.flush();
				msgout=t.nextLine();
				dos.writeUTF(msgout);
				dos.flush();

				// 05. Receive the result
				msgin = dis.readUTF();
				System.out.println("Result = "+ msgin);
			}

			if(c.equals("3"))
			{
				// sort(arrayA)

				// 06. Send the number of elements in array
				System.out.println("\nEnter the number of elements in array:");
				msgout = t.nextLine();
				dos.writeUTF(msgout);
				dos.flush();

				n = Integer.parseInt(msgout);

				System.out.println("\nEnter the elements in array:");
				for(i=0;i<n;i++)
				{
					// 07. Send each element
					msgout = t.nextLine();
					dos.writeUTF(msgout);
					dos.flush();
				}

				System.out.println("\nSorted array:");
				for(i=0;i<n;i++)
				{
					// 08. Receive each element
					msgin = dis.readUTF();
					System.out.print(msgin+" ");
				}
			}

			if(c.equals("4"))
			{
				// Matrix multiplication

				// Accept matrices from the user
				System.out.println("\nEnter elements row-by-row for Matrix A:");
				for(i=0;i<9;i++)
				{
					n = t.nextInt();

					// 09. Send each element of Matrix A
					dos.writeInt(n);
				}

				System.out.println("\nEnter elements row-by-row for Matrix B:");
				for(i=0;i<9;i++)
				{
					n = t.nextInt();

					// 10. Send each element of Matrix B
					dos.writeInt(n);
				}

				System.out.println("\nEnter elements row-by-row for Matrix C:");
				for(i=0;i<9;i++)
				{
					n = t.nextInt();

					// 11. Send each element of Matrix C
					dos.writeInt(n);
				}

				System.out.println("\nResulted matrix:");
				for(i=0;i<3;i++)
				{
					for(j=0;j<3;j++)
					{
						// 12. Receive elements of the resulted matrix
						n = dis.readInt();

						System.out.print(n+"\t");
					}
					System.out.println("");
				}
			}

			if(c.equals("0"))
			{
				// EXIT

				// 13. Receive text message
				msgin = dis.readUTF();

				System.out.println(msgin);
				break;
			}
		}// while loop

		t.close();
		s.close();
	}
}
