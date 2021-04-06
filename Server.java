import java.io.*;
import java.net.*;

public class Server
{
	public static void main(String args[]) throws Exception
	{
		// Create server socket and accept client's connection
		ServerSocket ss = new ServerSocket(1422);
		Socket s = ss.accept();

		// Create text output and input streams
		DataInputStream eis = new DataInputStream(s.getInputStream());
		DataOutputStream eos = new DataOutputStream(s.getOutputStream());

		// 01. Send connection message
		eos.writeUTF("\nConnection Established");
		eos.flush();

		int ai, bi, i, n, ar[], j, a[][] = new int[3][3], b[][] = new int[3][3], c[][] = new int[3][3], nn[][] = new int[3][3], mm[][] = new int[3][3], k;
		String ch, as, bs;

		while(true)
		{
			// 02. Receive choice
			ch = eis.readUTF();

			if(ch.equals("1"))
			{
				// calculate_pi()

				// 03. Send the value of Pi
				eos.writeUTF("\nThe value of Pi is (22/7) which is 3.14159265359");
			}

			if(ch.equals("2"))
			{
				// add(i, j)

				// 04. Receive two numbers
				as = eis.readUTF();
				bs = eis.readUTF();

				ai = Integer.parseInt(as);
				bi = Integer.parseInt(bs);

				ai = ai + bi;

				as = ""+ai;

				// 05. Send the result
				eos.writeUTF(as);
				eos.flush();
			}

			if(ch.equals("3"))
			{
				// sort(arrayA)

				// 06. Receive the number of elements in array
				as = eis.readUTF();

				n = Integer.parseInt(as);
				ar = new int[n];

				for(i=0;i<n;i++)
				{
					// 07. Receive each element
					as = eis.readUTF();

					ar[i] = Integer.parseInt(as);
				}

				// Bubble sort
				for (i=0; i < n-1; i++)
					for (j=0; j < n-i-1; j++)
						if(ar[j] > ar[j+1])
						{
							ai = ar[j];
							ar[j] = ar[j+1];
							ar[j+1] = ai;
						}

				for(i=0;i<n;i++)
				{
					// 08. Send each element
					as = ""+ar[i];
					eos.writeUTF(as);
					eos.flush();
				}
			}

			if(ch.equals("4"))
			{
				// matrix_multiply(matrixA, matrixB, matrixC)

				// 09. Accept elements of matrix A
				for(i=0;i<3;i++)
					for(j=0;j<3;j++)
						a[i][j] = eis.readInt();

				// 10. Accept elements of matrix B
				for(i=0;i<3;i++)
					for(j=0;j<3;j++)
						b[i][j] = eis.readInt();

				// 11. Accept elements of matrix C
				for(i=0;i<3;i++)
					for(j=0;j<3;j++)
						c[i][j] = eis.readInt();

				for(i=0;i<3;i++)
					for(j=0;j<3;j++)
					{
						nn[i][j]=0;
						for(k=0;k<3;k++)
							nn[i][j] = (a[i][k] * b[k][j]) + nn[i][j];
					}

				for(i=0;i<3;i++)
					for(j=0;j<3;j++)
					{
						mm[i][j]=0;
						for(k=0;k<3;k++)
							mm[i][j] = (nn[i][k] * c[k][j]) + mm[i][j];

						// 12. Send elements of the resulted matrix
						eos.writeInt(mm[i][j]);
					}
			}

			if(ch.equals("0"))
			{
				// EXIT

				// 13. Send text message
				eos.writeUTF("\nConnection closed");
				eos.flush();

				break;
			}
		}

		s.close();
		ss.close();
	}
}
