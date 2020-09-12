package importer;

import domain.Message;
import org.junit.Test;
import utils.TestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static org.junit.Assert.*;

public class ImporterTest {

    @Test
    public void testShowFormWhenMissingArgs() {
        Args args = TestUtils.buildMysqlArgs();
        args.setDatabase(null);

        boolean showForm = new Importer(args).showForm();
        assertTrue(showForm);
    }

    @Test
    public void testHideFormWhenAllArgs() {
        Args args = TestUtils.buildMysqlArgs();

        boolean showForm = new Importer(args).showForm();
        assertFalse(showForm);
    }

    @Test
    public void testLoadMessagesMysql() throws ParseException {
        Args args = TestUtils.buildMysqlArgs();

        List<Message> messages = new Importer(args).loadMessages();
        assertFalse(messages.isEmpty());
        assertEquals(2, messages.size());
        Message message = messages.get(0);
        assertEquals("ISPXNB32R82Y766F", message.getFiscalCode());
        assertEquals("Welcome to IO, Giovanni", message.getSubject());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-30").getTime(), message.getDueDate().longValue());
        assertEquals("# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.", message.getMarkdown());
        assertEquals(0, message.getAmount());
        assertEquals(1, message.getNoticeNumber());
        assertEquals(false, message.getInvalidAfterDueDate());
    }

    @Test
    public void testShowFormWhenMissingArgsOracle() {
        Args args = TestUtils.buildOracleArgs();
        args.setSid(null);

        boolean showForm = new Importer(args).showForm();
        assertTrue(showForm);
    }

    @Test
    public void testHideFormWhenAllArgsOracle() {
        Args args = TestUtils.buildOracleArgs();

        boolean showForm = new Importer(args).showForm();
        assertFalse(showForm);
    }

    @Test
    public void testLoadMessagesOracle() throws ParseException {
        Args args = TestUtils.buildOracleArgs();

        List<Message> messages = new Importer(args).loadMessages();
        assertFalse(messages.isEmpty());
        assertEquals(2, messages.size());
        Message message = messages.get(0);
        assertEquals("ISPXNB32R82Y766F", message.getFiscalCode());
        assertEquals("Welcome to IO, Giovanni", message.getSubject());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-30").getTime(), message.getDueDate().longValue());
        assertEquals("# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.", message.getMarkdown());
        assertEquals(0, message.getAmount());
        assertEquals(1, message.getNoticeNumber());
        assertEquals(false, message.getInvalidAfterDueDate());
    }

}
