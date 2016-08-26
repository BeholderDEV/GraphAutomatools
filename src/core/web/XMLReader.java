package core.web;

/*
 * Informe abaixo o pacote em que este arquivo está 
 * A informação de pacote deve ser a primeira do arquivo 
 * Obs.:
 *  Se importou o arquivo por uma IDE (Netbeans, Eclipse,...) A informação de
 *  pacote pode já estar presente, neste caso ignore esta mensagem. 
 */

//package ???;

import core.model.Aresta;
import core.model.Grafo;
import core.model.Vertice;
import java.awt.Point;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * Use este código para criar seu grafo a partir do xml gerado na aba anterior
 * Passos: 
 *  1 - Salve o xml em um arquivo 
 *  2 - Exporte este código para seu projeto 
 *  3 - Altere o método grafoFromXML incluindo uma referência à 
 *      classe do seu grafo, conforme exemplificado no código.
 *      Apenas as linhas comentadas precisam ser alteradas.
 *  4 - Execute este código conforme demonstrado no método main, logo abaixo
 *  5 - Uma janela de seleção de arquivo abrirá, selecione o arquivo xml salvo no passo 1
 */

public class XMLReader {

    public static Grafo grafoFromXML() {
        Grafo grafo= new Grafo();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
        fileChooser.setFileFilter(extensionFilter);
        File file = null;
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
                NodeList nodes = doc.getElementsByTagName("Vertice");
                if (nodes.getLength() == 0) {
                    System.out.println("Você não está importando o xml correto. \n"
                            + "O xml esperado é aquele gerado a partir do botão 'Scripts' (na janela principal do programa). \n"
                            + "- Passos: \n"
                            + "1) clique sobre o botão 'Scripts' na tela principal do GraphMax\n"
                            + "2) Acesse a aba 'XML' na janela que foi aberta\n"
                            + "3) Clique no botão 'Exportar'");
                } else {
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Node node = nodes.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            int relId = Integer.parseInt(element.getAttribute("relId"));
                            String rotulo = element.getAttribute("rotulo");
                            int posX = Integer.parseInt(element.getAttribute("posX"));
                            int posY = Integer.parseInt(element.getAttribute("posY"));
                            grafo.addVertice(new Vertice(relId,rotulo, new Point(posX, posY)));
                        }
                    }
                    nodes = doc.getElementsByTagName("Aresta");
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Node node = nodes.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            int idVertice1 = Integer.parseInt(element.getAttribute("idVertice1"));
                            int idVertice2 = Integer.parseInt(element.getAttribute("idVertice2"));
                            double peso = Double.parseDouble(element.getAttribute("peso"));
                            grafo.addAresta(new Aresta(grafo.getVertice(idVertice1), grafo.getVertice(idVertice2), peso));
                        }
                    }
                    return grafo;
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return null;
    }
}
