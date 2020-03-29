package journal.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import journal.dao.ApplicationDAO;
import journal.model.application;



@WebServlet("/addApplication")
public class addapplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationDAO ApplicationDAO;
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String ptitle = request.getParameter("ptitle");
		String jrname = request.getParameter("jrname");
		String doi = request.getParameter("doi");
		String jcat = request.getParameter("jcat");
		String firstauthor = request.getParameter("firstauthor");
		String fauthor = request.getParameter("fauthor");
		String seauthor = request.getParameter("seauthor");
		String secondauthor = request.getParameter("secondauthor");
		String sauthor = request.getParameter("sauthor");
		String thauthor = request.getParameter("thauthor");
		String thirdauthor = request.getParameter("thirdauthor");
		String tauthor = request.getParameter("tauthor");
		String otherauthor = request.getParameter("otherauthor");
		Date today = new Date();
		String date = today.toString();
		String refdate1 = date.replaceAll("\\s+", "");
		String refdate = refdate1.toUpperCase();
		
		if ((ptitle == null || ptitle.contentEquals("") || ptitle.contentEquals(" ")) || 
			(jrname == null || jrname.equals("") || jrname.contentEquals(" ")) ||
			(doi == null || doi.equals("") || doi.contentEquals(" ")) ||
			(jcat == null || jcat.contentEquals("")) ||
			(firstauthor == null || firstauthor.contentEquals("") || firstauthor.contentEquals(" ")) ||
			(fauthor == null || fauthor.contentEquals(""))
		//	(secondauthor == null || secondauthor.contentEquals("")) ||
		//	(sauthor == null || sauthor.contentEquals("")) ||
		//	(thirdauthor == null || thirdauthor.contentEquals("")) ||
		//	(tauthor == null || tauthor.contentEquals(""))
				){
			request.setAttribute("error", "Fill up Mandatory fields");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/applications.jsp");
			rd.forward(request, response);
		} else { 
			application a = new application();
			
			
			a.setREFNO(refdate);
			a.setTitle(ptitle);
			a.setjournal(jrname);
			a.setDOI(doi);
			a.setJCAT(jcat);
			a.setFIRSTAUTHOR(firstauthor);
			a.setFAUTHOR(fauthor);
			a.setSEAUTHOR(seauthor);
			a.setSECONDAUTHOR(secondauthor);
			a.setSAUTHOR(sauthor);
			a.setTHAUTHOR(thauthor);
			a.setTHIRDAUTHOR(thirdauthor);
			a.setTAUTHOR(tauthor);
			a.setOTHERAUTHOR(otherauthor);
			
			if(jcat.contentEquals("Q1") && firstauthor.contentEquals("Faculty") && seauthor.contentEquals("no") && thauthor.contentEquals("no")) {
				a.setTOTALINCENTIVE("25000");
			} else if(jcat.contentEquals("Q1") && seauthor.contentEquals("yes") && thauthor.contentEquals("yes"))	{
				a.setTOTALINCENTIVE("15000");
			} else if(jcat.contentEquals("Q2")) {
				a.setTOTALINCENTIVE("10000");
			} else if(jcat.contentEquals("Q3"))	{
				a.setTOTALINCENTIVE("5000");
			} else if(jcat.contentEquals("Q4"))	{
				a.setTOTALINCENTIVE("2000");
			}
			// incentives calculation for condition 1 
			
			if(fauthor.contentEquals("Faculty") && seauthor.contentEquals("NIL") && thauthor.contentEquals("NIL"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("25000");
					a.setINCENTIVES("0");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("10000");
					a.setINCENTIVES("0");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("0");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("2000");
					a.setINCENTIVES("0");
					a.setINCENTIVET("0");
				}
			} 
			
			// incentives calculation for condition 2a
			
			else if(((fauthor.contentEquals("Faculty") && sauthor.contentEquals("Student")) || (fauthor.contentEquals("Faculty") && sauthor.contentEquals("External"))) && thauthor.contentEquals("NIL"))
				{
					if(jcat.contentEquals("Q1"))
					{
						a.setINCENTIVEF("15000");
						a.setINCENTIVES("0");
						a.setINCENTIVET("0");
					}
					else if(jcat.contentEquals("Q2"))
					{
						a.setINCENTIVEF("10000");
						a.setINCENTIVES("0");
						a.setINCENTIVET("0");
					}
					else if(jcat.contentEquals("Q3"))
					{
						a.setINCENTIVEF("5000");
						a.setINCENTIVES("0");
						a.setINCENTIVET("0");
					}
					else if(jcat.contentEquals("Q4"))
					{
						a.setINCENTIVEF("2000");
						a.setINCENTIVES("0");
						a.setINCENTIVET("0");
					}
				}
			/// lab exam
			else if(((fauthor.contentEquals("Faculty") && sauthor.contentEquals("FullTimeStudent")) || (fauthor.contentEquals("FullTimeStudent") && sauthor.contentEquals("Faculty"))) && thauthor.contentEquals("NIL"))
					{
						if(jcat.contentEquals("Q1"))
						{
							a.setINCENTIVEF("7500");
							a.setINCENTIVES("7500");
							a.setINCENTIVET("0");
							//a.setINCENTIVEFT("7500");
						}
						else if(jcat.contentEquals("Q2"))
						{
							a.setINCENTIVEF("5000");
							a.setINCENTIVES("5000");
							a.setINCENTIVET("0");
							//a.setINCENTIVEFT("5000");
						}
						else if(jcat.contentEquals("Q3"))
						{
							a.setINCENTIVEF("2500");
							a.setINCENTIVES("2500");
							a.setINCENTIVET("0");
							//a.setINCENTIVEFT("2500");
						}
						else if(jcat.contentEquals("Q4"))
						{
							a.setINCENTIVEF("1000");
							a.setINCENTIVES("1000");
							a.setINCENTIVET("0");
							//a.setINCENTIVEFT("1000");
						}
					}
			
			// incentives calculation for condition 2b
			
			else if(((fauthor.contentEquals("Student") || fauthor.contentEquals("External")) && sauthor.contentEquals("Faculty")) && thauthor.contentEquals("NIL"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("15000");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("10000");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("5000");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("2000");
					a.setINCENTIVET("0");
				}
			}
			//lab exam
			else if(((fauthor.contentEquals("FullTimeStudent")) && sauthor.contentEquals("Faculty")) && thauthor.contentEquals("NIL")) {
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("7500");
					a.setINCENTIVET("0");
					
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("5000");
					a.setINCENTIVET("0");
					
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("1000");
					a.setINCENTIVET("0");
					
				}
			}
			
			// incentives calculation for condition 2c
			else if((fauthor.contentEquals("Faculty") && sauthor.contentEquals("Faculty") && thauthor.contentEquals("NIL")))
					{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("7500");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("5000");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("1000");
					a.setINCENTIVET("0");
				}
					}
			
			//lab exam
			
			else if((fauthor.contentEquals("Faculty") && sauthor.contentEquals("FullTimeStudent") && thauthor.contentEquals("NIL")))
			{
						if(jcat.contentEquals("Q1"))
						{
							a.setINCENTIVEF("7500");
							a.setINCENTIVES("7500");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q2"))
						{
							a.setINCENTIVEF("5000");
							a.setINCENTIVES("5000");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q3"))
						{
							a.setINCENTIVEF("2500");
							a.setINCENTIVES("2500");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q4"))
						{
							a.setINCENTIVEF("1000");
							a.setINCENTIVES("1000");
							a.setINCENTIVET("0");
						}
			}
			
			// lab exam
			else if(((fauthor.contentEquals("Faculty") || fauthor.contentEquals("FullTimeStudent")) && sauthor.contentEquals("FullTimeStudent") && thauthor.contentEquals("NIL")))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("7500");
					a.setINCENTIVET("0");
					
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("5000");
					a.setINCENTIVET("0");
					
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("0");
					
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("1000");
					a.setINCENTIVET("0");
					
				}
			}
			
			// incentives calculation for condition 3a
			else if(fauthor.contentEquals("Faculty") && (sauthor.contentEquals("Student") || sauthor.contentEquals("External")) && (tauthor.contentEquals("Student") || tauthor.contentEquals("External"))) 
					{
						if(jcat.contentEquals("Q1"))
						{
							a.setINCENTIVEF("15000");
							a.setINCENTIVES("0");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q2"))
						{
							a.setINCENTIVEF("10000");
							a.setINCENTIVES("0");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q3"))
						{
							a.setINCENTIVEF("5000");
							a.setINCENTIVES("0");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q4"))
						{
							a.setINCENTIVEF("2000");
							a.setINCENTIVES("0");
							a.setINCENTIVET("0");
						}
					}
			
			//lab exam
			
			else if(
					
					(fauthor.contentEquals("Faculty") && sauthor.contentEquals("FullTimeStudent")) && 
					(tauthor.contentEquals("Student") || tauthor.contentEquals("External")))
			{
							if(jcat.contentEquals("Q1"))
							{
								a.setINCENTIVEF("7500");
								a.setINCENTIVES("7500");
								a.setINCENTIVET("0");
							}
							else if(jcat.contentEquals("Q2"))
							{
								a.setINCENTIVEF("5000");
								a.setINCENTIVES("5000");
								a.setINCENTIVET("0");
							}
							else if(jcat.contentEquals("Q3"))
							{
								a.setINCENTIVEF("2500");
								a.setINCENTIVES("2500");
								a.setINCENTIVET("0");
							}
							else if(jcat.contentEquals("Q4"))
							{
								a.setINCENTIVEF("1000");
								a.setINCENTIVES("1000");
								a.setINCENTIVET("0");
							}
			}
			
			
			// incentives calculation for condition 3b
			else if(fauthor.contentEquals("Student") && sauthor.contentEquals("Faculty") && (tauthor.contentEquals("Student") || tauthor.contentEquals("External")))
				
					{
						if(jcat.contentEquals("Q1"))
						{
							a.setINCENTIVEF("0");
							a.setINCENTIVES("15000");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q2"))
						{
							a.setINCENTIVEF("0");
							a.setINCENTIVES("10000");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q3"))
						{
							a.setINCENTIVEF("0");
							a.setINCENTIVES("5000");
							a.setINCENTIVET("0");
						}
						else if(jcat.contentEquals("Q4"))
						{
							a.setINCENTIVEF("0");
							a.setINCENTIVES("2000");
							a.setINCENTIVET("0");
						}
					}
			
			// incentives calculation for condition 3c
			
			else if(fauthor.contentEquals("External") && sauthor.contentEquals("Faculty") && (tauthor.contentEquals("Student") || tauthor.contentEquals("External)")))
					{
							if(jcat.contentEquals("Q1"))
							{
								a.setINCENTIVEF("0");
								a.setINCENTIVES("7500");
								a.setINCENTIVET("0");
							}
							else if(jcat.contentEquals("Q2"))
							{
								a.setINCENTIVEF("0");
								a.setINCENTIVES("5000");
								a.setINCENTIVET("0");
							}
							else if(jcat.contentEquals("Q3"))
							{
								a.setINCENTIVEF("0");
								a.setINCENTIVES("2500");
								a.setINCENTIVET("0");
							}
							else if(jcat.contentEquals("Q4"))
							{
								a.setINCENTIVEF("0");
								a.setINCENTIVES("1000");
								a.setINCENTIVET("0");
							}
					}
			
			// incentive calculation for condition 3d
			else if((fauthor.contentEquals("Student") || fauthor.contentEquals("External")) && (sauthor.contentEquals("Student") || sauthor.contentEquals("External")) && tauthor.contentEquals("Faculty"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("0");
					a.setINCENTIVET("7500");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("0");
					a.setINCENTIVET("5000");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("0");
					a.setINCENTIVET("2500");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("0");
					a.setINCENTIVET("1000");
				}
			}
			
			// incentive calculation for condition 3e
			
			else if(fauthor.contentEquals("Faculty") && sauthor.contentEquals("Faculty") && (tauthor.contentEquals("Student") || tauthor.contentEquals("External")))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("7500");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("5000");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("0");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("1000");
					a.setINCENTIVET("0");
				}
			}
			
			// incentives calculation for condition 3f
			
			else if(fauthor.contentEquals("Faculty") && (sauthor.contentEquals("Student") || sauthor.contentEquals("External")) && tauthor.contentEquals("Faculty"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("0");
					a.setINCENTIVET("7500");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("0");
					a.setINCENTIVET("5000");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("0");
					a.setINCENTIVET("2500");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("0");
					a.setINCENTIVET("1000");
				}
			}
			
			// incentives calculation for condition 3g
			
			else if(fauthor.contentEquals("Student") && sauthor.contentEquals("Faculty") && tauthor.contentEquals("Faculty"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("7500");
					a.setINCENTIVET("7500");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("5000");
					a.setINCENTIVET("5000");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("2500");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("1000");
					a.setINCENTIVET("1000");
				}
			}
			
			// incentive calculation for condition 3h
			
			else if(fauthor.contentEquals("External") && sauthor.contentEquals("Faculty") && tauthor.contentEquals("Faculty"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("3750");
					a.setINCENTIVET("3750");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("2500");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("1250");
					a.setINCENTIVET("1250");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("0");
					a.setINCENTIVES("250");
					a.setINCENTIVET("250");
				}
			}
			
			// incentive calculation for condition 3i
			
			else if(fauthor.contentEquals("Faculty") && sauthor.contentEquals("Faculty") && tauthor.contentEquals("Faculty"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("3750");
					a.setINCENTIVET("3750");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("2500");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("1250");
					a.setINCENTIVET("1250");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("500");
					a.setINCENTIVET("500");
				}
			} 
			
			//lab exam
			else if(fauthor.contentEquals("Faculty") && sauthor.contentEquals("Faculty") && tauthor.contentEquals("FullTimeStudent"))
			{
				if(jcat.contentEquals("Q1"))
				{
					a.setINCENTIVEF("7500");
					a.setINCENTIVES("3750");
					a.setINCENTIVET("3750");
				}
				else if(jcat.contentEquals("Q2"))
				{
					a.setINCENTIVEF("5000");
					a.setINCENTIVES("2500");
					a.setINCENTIVET("2500");
				}
				else if(jcat.contentEquals("Q3"))
				{
					a.setINCENTIVEF("2500");
					a.setINCENTIVES("1250");
					a.setINCENTIVET("1250");
				}
				else if(jcat.contentEquals("Q4"))
				{
					a.setINCENTIVEF("1000");
					a.setINCENTIVES("500");
					a.setINCENTIVET("500");
				}
			} 
			
			try {
				ApplicationDAO.addApplication(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Application Added Successfully with id="+a.getId());
			String idd = a.getId();
			request.setAttribute("refidd", idd);
			request.setAttribute("success", "Application Added Successfully");
			List<application> applications = null;
			try {
				applications = ApplicationDAO.listAllApplications();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("Applications", applications);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/viewApplication.jsp");
			rd.forward(request, response);
		}
	}

}
