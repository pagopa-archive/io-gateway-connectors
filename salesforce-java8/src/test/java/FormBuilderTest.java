import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class FormBuilderTest {


    @Before
    public void setUp(){

    }

    @Test
    public void testFields() throws Exception{
        JsonObject form = new FormBuilder().build();
        assertNotNull( form.get("form") );
        assertNotNull( form.get("form").getAsJsonArray().size() == 6 );
    }

}
