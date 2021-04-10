package sample.datamodel;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class MemoData {
    private static final String MEMOS_FILE = "memos.xml";
    private static String LOCAL_MEMOS_FILE = "C:\\Users\\MK\\Desktop\\test.xml";

    private static final String MEMO = "memo";
    private static final String DATE = "kuupaev";
    private static final String TIITEL = "tiitel";
    private static final String SISU = "sisu";

    private ObservableList<Memo> memos;


    public MemoData() {
        memos = FXCollections.observableArrayList();
    }

    public ObservableList<Memo> getContacts(){
        return memos;
    }

    public void addMemo(Memo item){
        memos.add(item);
    }

    public void deleteMemo(Memo item){
        memos.remove(item);
    }

    public void deleteMemos(){
        for (int i = 0;i <= memos.size()+1;i++){
            memos.remove(0);
        }
    }

    public void loadMemos() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(LOCAL_MEMOS_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Memo memo = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a memo item, we create a new memo
                    if (startElement.getName().getLocalPart().equals(MEMO)) {
                        memo = new Memo();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(TIITEL)) {
                            event = eventReader.nextEvent();
                            memo.setTiitel(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(DATE)) {
                        event = eventReader.nextEvent();
                        memo.setKuupaev(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SISU)) {
                        event = eventReader.nextEvent();
                        memo.setSisu(event.asCharacters().getData());
                        continue;
                    }
                }

                // If we reach the end of a memo element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(MEMO)) {
                        memos.add(memo);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveMemos() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(LOCAL_MEMOS_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement memosStartElement = eventFactory.createStartElement("",
                    "", "memos");
            eventWriter.add(memosStartElement);
            eventWriter.add(end);

            for (Memo memo: memos) {
                saveMemo(eventWriter, eventFactory, memo);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "memos"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Memos file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing memo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveMemo(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Memo memo)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create contact open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", MEMO);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, DATE, memo.getKuupaev());
        createNode(eventWriter, TIITEL, memo.getTiitel());
        createNode(eventWriter, SISU, memo.getSisu());

        eventWriter.add(eventFactory.createEndElement("", "", MEMO));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

    public static void setLocalMemosFile(String localMemosFile) {
        LOCAL_MEMOS_FILE = localMemosFile;
    }
}
