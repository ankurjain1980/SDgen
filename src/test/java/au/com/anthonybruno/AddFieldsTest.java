package au.com.anthonybruno;

import au.com.anthonybruno.defintion.FieldDefinition;
import au.com.anthonybruno.defintion.RecordDefinition;
import au.com.anthonybruno.generator.defaults.StringGenerator;
import au.com.anthonybruno.settings.CsvSettings;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AddFieldsTest {

    private final int rowsToGenerate = 5;

    @Test
    public void generateSingleFieldNotEmpty() {
        String result = generateSingleFieldCsv();
        assertFalse(result.isEmpty());
    }

    @Test
    public void generateSingleFieldsCorrectAmountOfRows() {
        String result = generateSingleFieldCsv();
        assertEquals(rowsToGenerate, result.split("\n").length);
    }

    @Test
    public void generateLotsOfFields() {
        FieldDefinition fieldDefinition = Gen.start();
        RecordDefinition recordDefinition = null;
        for (int i = 0; i < 100; i++) {
            recordDefinition = fieldDefinition.addField("Field " + i, new StringGenerator());
        }
        String result = recordDefinition.generate(1000).asCsv().toStringForm();

        String firstLine = result.substring(0, result.indexOf("\n"));
        assertEquals(100,  firstLine.split(",").length);


    }

    public String generateSingleFieldCsv() {
        return Gen.start()
                .addField("Name", new StringGenerator())
                .generate(rowsToGenerate)
                .asCsv(new CsvSettings(false))
                .toStringForm();
    }
}
