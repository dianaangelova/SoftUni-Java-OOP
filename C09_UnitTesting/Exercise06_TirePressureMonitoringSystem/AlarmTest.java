package C09_UnitTesting.Exercise06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class AlarmTest {

    @Test
    public void test_AlarmWithLowPressure() {
        Sensor sensorLow;
        sensorLow = Mockito.<Sensor>mock(Sensor.class);
        when(sensorLow.popNextPressurePsiValue()).thenReturn(10.0);

        Alarm alarm = new Alarm(sensorLow);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void test_AlarmWithHighPressure() {
        Sensor sensorHigh = Mockito.mock(Sensor.class);
        when(sensorHigh.popNextPressurePsiValue()).thenReturn(100.0);
        Alarm alarm = new Alarm(sensorHigh);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void test_AlarmWithNormalPressure() {
        Sensor sensorNormal = Mockito.mock(Sensor.class);
        when(sensorNormal.popNextPressurePsiValue()).thenReturn(20.0);
        Alarm alarm = new Alarm(sensorNormal);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }

}