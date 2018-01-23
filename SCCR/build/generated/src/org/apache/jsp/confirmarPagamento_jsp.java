package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class confirmarPagamento_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("               \n");
      out.write(" \n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><link rel=\"stylesheet\" type=\"text/css\" href=\"folhaEstilo.css\">\n");
      out.write("        <title>Confirmar Pagamento</title>\n");
      out.write("        <link href=\"css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Confirmar Pagamento</h1>\n");
      out.write("\n");
      out.write("        <form  action=\"ManterPagamentoController?acao=confirmarPagamento\" method=\"post\" name=\"frmManterPagamento\">\n");
      out.write("            <table  id=\"tabelaManter\">\n");
      out.write("                <tr >\n");
      out.write("                    <td>Código de barra:</td> \n");
      out.write("                    <td><input type=\"text\" name=\"txtCodigoBarra\" value=\"\"></td>\n");
      out.write("                    </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td><input type=\"submit\" name=\"btnConfirmar\" value=\"Confirmar Pagamento\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <a href=\"index.jsp\">\n");
      out.write("                    <input type=\"button\" value=\"Voltar\" />\n");
      out.write("                </a>\n");
      out.write("            </table>\n");
      out.write("                    \n");
      out.write("                        \n");
      out.write("        </form>\n");
      out.write("        <SCRIPT language=\"JavaScript\">\n");
      out.write("         \n");
      out.write("        </SCRIPT>        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
