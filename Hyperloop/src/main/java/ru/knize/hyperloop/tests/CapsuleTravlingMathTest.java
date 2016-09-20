package ru.knize.hyperloop.tests;

/**
 * Created by knize on 15.09.16.
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ru.knize.hyperloop.CapsuleTravelingMath;


public class CapsuleTravlingMathTest {
    CapsuleTravelingMath ctm;

    @Before
    public void prepare()
    {ctm = new CapsuleTravelingMath();}

    @Test
    public void testComputePrice1000(){ assertEquals(80.0, ctm.computePrice(1000), 0.01);}

    @Test
    public void testComputePrice322(){ assertEquals(32.2, ctm.computePrice(322), 0.01);}

    @Test
    public void testComputePrice4000(){ assertEquals(280.0, ctm.computePrice(4000), 0.01);}

    @Test
    public void testComputePrice9000(){ assertEquals(540.0, ctm.computePrice(9000), 0.01);}

    @Test
    public void testComputeTime1000(){ assertEquals(3233.0, ctm.computeTime(1000).getSeconds(), 1);}

    @Test
    public void testComputeTime322(){ assertEquals(1199.0, ctm.computeTime(322).getSeconds(), 1);}

    @Test
    public void testComputeTime4000(){ assertEquals(12233.0, ctm.computeTime(4000).getSeconds(), 1);}

    @Test
    public void testComputeTime9000(){ assertEquals(27233.0, ctm.computeTime(9000).getSeconds(), 1);}



}
