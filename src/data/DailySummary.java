package data;

import org.joda.time.LocalTime;
import org.joda.time.LocalDate;

/**
 * DailySummary class is an abstraction for DailySummary1 and DailySummary2. The
 * data is already formatted to user specified unit (fahrenheit or celsius) and
 * checked for undefined values.
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DailySummary {
	// Properties.
	private LocalDate date;
	private Integer dataSpan;
	private Double windRun;
	private Double dailyRain;
	private Double dailyUVDose;
	private Integer numWindPackets;
	private Double dailySolarEnergy;
	private Integer minSunLight;
	private Double dailyETTotal;
	private Double integratedHeatDD65;
	private Integer[] windDirectionDistribution = new Integer[16];
	private Double integratedCoolDD65;

	// Maximum values.
	private Double maxOutTemp;
	private LocalTime timeMaxOutTemp;
	private Double maxInTemp;
	private LocalTime timeMaxInTemp;
	private Double maxWindChill;
	private LocalTime timeMaxWindChill;
	private Double maxDewPoint;
	private LocalTime timeMaxDewPoint;
	private Integer maxOutHum;
	private LocalTime timeMaxOutHum;
	private Integer maxInHum;
	private LocalTime timeMaxInHum;
	private Double maxPressure;
	private LocalTime timeMaxPressure;
	private Double maxWindSpeed;
	private Double maxWindSpeedDir;
	private LocalTime timeMaxWindSpeed;
	private Double maxAvg10MinWindSpeed;
	private Double maxAvg10MinWindSpeedDir;
	private LocalTime timeMaxAvg10MinWindSpeed;
	private Double maxRainRate;
	private LocalTime timeMaxRainRate;
	private Double maxUV;
	private LocalTime timeMaxUV;
	private Double maxSolar;
	private LocalTime timeMaxSolar;
	private Double maxHeatIndex;
	private LocalTime timeMaxHeatIndex;
	private Double maxTHSWIndex;
	private LocalTime timeMaxTHSWIndex;
	private Double maxTHWIndex;
	private LocalTime timeMaxTHWIndex;

	// Minimum values.
	private Double minOutTemp;
	private LocalTime timeMinOutTemp;
	private Double minInTemp;
	private LocalTime timeMinInTemp;
	private Double minWindChill;
	private LocalTime timeMinWindChill;
	private Double minDewPoint;
	private LocalTime timeMinDewPoint;
	private Integer minOutHum;
	private LocalTime timeMinOutHum;
	private Integer minInHum;
	private LocalTime timeMinInHum;
	private Double minPressure;
	private LocalTime timeMinPressure;
	private Double minHeatIndex;
	private LocalTime timeMinHeatIndex;
	private Double minTHSWIndex;
	private LocalTime timeMinTHSWIndex;
	private Double minTHWIndex;
	private LocalTime timeMinTHWIndex;

	// Average values.
	private Double avgOutTemp;
	private Double avgInTemp;
	private Double avgWindChill;
	private Double avgDewPoint;
	private Integer avgOutHum;
	private Double avgPressure;
	private Double avgWindSpeed;
	private Double avgHeatIndex;

	/**
	 * Empty constructor.
	 */
	public DailySummary() {

	}

	/**
	 * @param date
	 * @param dataSpan
	 * @param windRun
	 * @param dailyRain
	 * @param dailyUVDose
	 * @param numWindPackets
	 * @param dailySolarEnergy
	 * @param minSunLight
	 * @param dailyETTotal
	 * @param integratedHeatDD65
	 * @param windDirectionDistribution
	 * @param integratedCoolDD65
	 * @param maxOutTemp
	 * @param timeMaxOutTemp
	 * @param maxInTemp
	 * @param timeMaxInTemp
	 * @param maxWindChill
	 * @param timeMaxWindChill
	 * @param maxDewPoint
	 * @param timeMaxDewPoint
	 * @param maxOutHum
	 * @param timeMaxOutHum
	 * @param maxInHum
	 * @param timeMaxInHum
	 * @param maxPressure
	 * @param timeMaxPressure
	 * @param maxWindSpeed
	 * @param maxWindSpeedDir
	 * @param timeMaxWindSpeed
	 * @param maxAvg10MinWindSpeed
	 * @param maxAvg10MinWindSpeedDir
	 * @param timeMaxAvg10MinWindSpeed
	 * @param maxRainRate
	 * @param timeMaxRainRate
	 * @param maxUV
	 * @param timeMaxUV
	 * @param maxSolar
	 * @param timeMaxSolar
	 * @param maxHeatIndex
	 * @param timeMaxHeatIndex
	 * @param maxTHSWIndex
	 * @param timeMaxTHSWIndex
	 * @param maxTHWIndex
	 * @param timeMaxTHWIndex
	 * @param minOutTemp
	 * @param timeMinOutTemp
	 * @param minInTemp
	 * @param timeMinInTemp
	 * @param minWindChill
	 * @param timeMinWindChill
	 * @param minDewPoint
	 * @param timeMinDewPoint
	 * @param minOutHum
	 * @param timeMinOutHum
	 * @param minInHum
	 * @param timeMinInHum
	 * @param minPressure
	 * @param timeMinPressure
	 * @param minHeatIndex
	 * @param timeMinHeatIndex
	 * @param minTHSWIndex
	 * @param timeMinTHSWIndex
	 * @param minTHWIndex
	 * @param timeMinTHWIndex
	 * @param avgOutTemp
	 * @param avgInTemp
	 * @param avgWindChill
	 * @param avgDewPoint
	 * @param avgOutHum
	 * @param avgPressure
	 * @param avgWindSpeed
	 * @param avgHeatIndex
	 */
	public DailySummary(LocalDate date, Integer dataSpan, Double windRun,
			Double dailyRain, Double dailyUVDose, Integer numWindPackets,
			Double dailySolarEnergy, Integer minSunLight, Double dailyETTotal,
			Double integratedHeatDD65, Integer[] windDirectionDistribution,
			Double integratedCoolDD65, Double maxOutTemp,
			LocalTime timeMaxOutTemp, Double maxInTemp,
			LocalTime timeMaxInTemp, Double maxWindChill,
			LocalTime timeMaxWindChill, Double maxDewPoint,
			LocalTime timeMaxDewPoint, Integer maxOutHum,
			LocalTime timeMaxOutHum, Integer maxInHum, LocalTime timeMaxInHum,
			Double maxPressure, LocalTime timeMaxPressure, Double maxWindSpeed,
			Double maxWindSpeedDir, LocalTime timeMaxWindSpeed,
			Double maxAvg10MinWindSpeed, Double maxAvg10MinWindSpeedDir,
			LocalTime timeMaxAvg10MinWindSpeed, Double maxRainRate,
			LocalTime timeMaxRainRate, Double maxUV, LocalTime timeMaxUV,
			Double maxSolar, LocalTime timeMaxSolar, Double maxHeatIndex,
			LocalTime timeMaxHeatIndex, Double maxTHSWIndex,
			LocalTime timeMaxTHSWIndex, Double maxTHWIndex,
			LocalTime timeMaxTHWIndex, Double minOutTemp,
			LocalTime timeMinOutTemp, Double minInTemp,
			LocalTime timeMinInTemp, Double minWindChill,
			LocalTime timeMinWindChill, Double minDewPoint,
			LocalTime timeMinDewPoint, Integer minOutHum,
			LocalTime timeMinOutHum, Integer minInHum, LocalTime timeMinInHum,
			Double minPressure, LocalTime timeMinPressure, Double minHeatIndex,
			LocalTime timeMinHeatIndex, Double minTHSWIndex,
			LocalTime timeMinTHSWIndex, Double minTHWIndex,
			LocalTime timeMinTHWIndex, Double avgOutTemp, Double avgInTemp,
			Double avgWindChill, Double avgDewPoint, Integer avgOutHum,
			Double avgPressure, Double avgWindSpeed, Double avgHeatIndex) {
		this.date = date;
		this.dataSpan = dataSpan;
		this.windRun = windRun;
		this.dailyRain = dailyRain;
		this.dailyUVDose = dailyUVDose;
		this.numWindPackets = numWindPackets;
		this.dailySolarEnergy = dailySolarEnergy;
		this.minSunLight = minSunLight;
		this.dailyETTotal = dailyETTotal;
		this.integratedHeatDD65 = integratedHeatDD65;
		this.windDirectionDistribution = windDirectionDistribution;
		this.integratedCoolDD65 = integratedCoolDD65;
		this.maxOutTemp = maxOutTemp;
		this.timeMaxOutTemp = timeMaxOutTemp;
		this.maxInTemp = maxInTemp;
		this.timeMaxInTemp = timeMaxInTemp;
		this.maxWindChill = maxWindChill;
		this.timeMaxWindChill = timeMaxWindChill;
		this.maxDewPoint = maxDewPoint;
		this.timeMaxDewPoint = timeMaxDewPoint;
		this.maxOutHum = maxOutHum;
		this.timeMaxOutHum = timeMaxOutHum;
		this.maxInHum = maxInHum;
		this.timeMaxInHum = timeMaxInHum;
		this.maxPressure = maxPressure;
		this.timeMaxPressure = timeMaxPressure;
		this.maxWindSpeed = maxWindSpeed;
		this.maxWindSpeedDir = maxWindSpeedDir;
		this.timeMaxWindSpeed = timeMaxWindSpeed;
		this.maxAvg10MinWindSpeed = maxAvg10MinWindSpeed;
		this.maxAvg10MinWindSpeedDir = maxAvg10MinWindSpeedDir;
		this.timeMaxAvg10MinWindSpeed = timeMaxAvg10MinWindSpeed;
		this.maxRainRate = maxRainRate;
		this.timeMaxRainRate = timeMaxRainRate;
		this.maxUV = maxUV;
		this.timeMaxUV = timeMaxUV;
		this.maxSolar = maxSolar;
		this.timeMaxSolar = timeMaxSolar;
		this.maxHeatIndex = maxHeatIndex;
		this.timeMaxHeatIndex = timeMaxHeatIndex;
		this.maxTHSWIndex = maxTHSWIndex;
		this.timeMaxTHSWIndex = timeMaxTHSWIndex;
		this.maxTHWIndex = maxTHWIndex;
		this.timeMaxTHWIndex = timeMaxTHWIndex;
		this.minOutTemp = minOutTemp;
		this.timeMinOutTemp = timeMinOutTemp;
		this.minInTemp = minInTemp;
		this.timeMinInTemp = timeMinInTemp;
		this.minWindChill = minWindChill;
		this.timeMinWindChill = timeMinWindChill;
		this.minDewPoint = minDewPoint;
		this.timeMinDewPoint = timeMinDewPoint;
		this.minOutHum = minOutHum;
		this.timeMinOutHum = timeMinOutHum;
		this.minInHum = minInHum;
		this.timeMinInHum = timeMinInHum;
		this.minPressure = minPressure;
		this.timeMinPressure = timeMinPressure;
		this.minHeatIndex = minHeatIndex;
		this.timeMinHeatIndex = timeMinHeatIndex;
		this.minTHSWIndex = minTHSWIndex;
		this.timeMinTHSWIndex = timeMinTHSWIndex;
		this.minTHWIndex = minTHWIndex;
		this.timeMinTHWIndex = timeMinTHWIndex;
		this.avgOutTemp = avgOutTemp;
		this.avgInTemp = avgInTemp;
		this.avgWindChill = avgWindChill;
		this.avgDewPoint = avgDewPoint;
		this.avgOutHum = avgOutHum;
		this.avgPressure = avgPressure;
		this.avgWindSpeed = avgWindSpeed;
		this.avgHeatIndex = avgHeatIndex;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the dataSpan
	 */
	public Integer getDataSpan() {
		return dataSpan;
	}

	/**
	 * @param dataSpan
	 *            the dataSpan to set
	 */
	public void setDataSpan(Integer dataSpan) {
		this.dataSpan = dataSpan;
	}

	/**
	 * @return the windRun
	 */
	public Double getWindRun() {
		return windRun;
	}

	/**
	 * @param windRun
	 *            the windRun to set
	 */
	public void setWindRun(Double windRun) {
		this.windRun = windRun;
	}

	/**
	 * @return the dailyRain
	 */
	public Double getDailyRain() {
		return dailyRain;
	}

	/**
	 * @param dailyRain
	 *            the dailyRain to set
	 */
	public void setDailyRain(Double dailyRain) {
		this.dailyRain = dailyRain;
	}

	/**
	 * @return the dailyUVDose
	 */
	public Double getDailyUVDose() {
		return dailyUVDose;
	}

	/**
	 * @param dailyUVDose
	 *            the dailyUVDose to set
	 */
	public void setDailyUVDose(Double dailyUVDose) {
		this.dailyUVDose = dailyUVDose;
	}

	/**
	 * @return the numWindPackets
	 */
	public Integer getNumWindPackets() {
		return numWindPackets;
	}

	/**
	 * @param numWindPackets
	 *            the numWindPackets to set
	 */
	public void setNumWindPackets(Integer numWindPackets) {
		this.numWindPackets = numWindPackets;
	}

	/**
	 * @return the dailySolarEnergy
	 */
	public Double getDailySolarEnergy() {
		return dailySolarEnergy;
	}

	/**
	 * @param dailySolarEnergy
	 *            the dailySolarEnergy to set
	 */
	public void setDailySolarEnergy(Double dailySolarEnergy) {
		this.dailySolarEnergy = dailySolarEnergy;
	}

	/**
	 * @return the minSunLight
	 */
	public Integer getMinSunLight() {
		return minSunLight;
	}

	/**
	 * @param minSunLight
	 *            the minSunLight to set
	 */
	public void setMinSunLight(Integer minSunLight) {
		this.minSunLight = minSunLight;
	}

	/**
	 * @return the dailyETTotal
	 */
	public Double getDailyETTotal() {
		return dailyETTotal;
	}

	/**
	 * @param dailyETTotal
	 *            the dailyETTotal to set
	 */
	public void setDailyETTotal(Double dailyETTotal) {
		this.dailyETTotal = dailyETTotal;
	}

	/**
	 * @return the integratedHeatDD65
	 */
	public Double getIntegratedHeatDD65() {
		return integratedHeatDD65;
	}

	/**
	 * @param integratedHeatDD65
	 *            the integratedHeatDD65 to set
	 */
	public void setIntegratedHeatDD65(Double integratedHeatDD65) {
		this.integratedHeatDD65 = integratedHeatDD65;
	}

	/**
	 * @return the windDirectionDistribution
	 */
	public Integer[] getWindDirectionDistribution() {
		return windDirectionDistribution;
	}

	/**
	 * @param windDirectionDistribution
	 *            the windDirectionDistribution to set
	 */
	public void setWindDirectionDistribution(Integer[] windDirectionDistribution) {
		this.windDirectionDistribution = windDirectionDistribution;
	}

	/**
	 * @return the integratedCoolDD65
	 */
	public Double getIntegratedCoolDD65() {
		return integratedCoolDD65;
	}

	/**
	 * @param integratedCoolDD65
	 *            the integratedCoolDD65 to set
	 */
	public void setIntegratedCoolDD65(Double integratedCoolDD65) {
		this.integratedCoolDD65 = integratedCoolDD65;
	}

	/**
	 * @return the maxOutTemp
	 */
	public Double getMaxOutTemp() {
		return maxOutTemp;
	}

	/**
	 * @param maxOutTemp
	 *            the maxOutTemp to set
	 */
	public void setMaxOutTemp(Double maxOutTemp) {
		this.maxOutTemp = maxOutTemp;
	}

	/**
	 * @return the timeMaxOutTemp
	 */
	public LocalTime getTimeMaxOutTemp() {
		return timeMaxOutTemp;
	}

	/**
	 * @param timeMaxOutTemp
	 *            the timeMaxOutTemp to set
	 */
	public void setTimeMaxOutTemp(LocalTime timeMaxOutTemp) {
		this.timeMaxOutTemp = timeMaxOutTemp;
	}

	/**
	 * @return the maxInTemp
	 */
	public Double getMaxInTemp() {
		return maxInTemp;
	}

	/**
	 * @param maxInTemp
	 *            the maxInTemp to set
	 */
	public void setMaxInTemp(Double maxInTemp) {
		this.maxInTemp = maxInTemp;
	}

	/**
	 * @return the timeMaxInTemp
	 */
	public LocalTime getTimeMaxInTemp() {
		return timeMaxInTemp;
	}

	/**
	 * @param timeMaxInTemp
	 *            the timeMaxInTemp to set
	 */
	public void setTimeMaxInTemp(LocalTime timeMaxInTemp) {
		this.timeMaxInTemp = timeMaxInTemp;
	}

	/**
	 * @return the maxWindChill
	 */
	public Double getMaxWindChill() {
		return maxWindChill;
	}

	/**
	 * @param maxWindChill
	 *            the maxWindChill to set
	 */
	public void setMaxWindChill(Double maxWindChill) {
		this.maxWindChill = maxWindChill;
	}

	/**
	 * @return the timeMaxWindChill
	 */
	public LocalTime getTimeMaxWindChill() {
		return timeMaxWindChill;
	}

	/**
	 * @param timeMaxWindChill
	 *            the timeMaxWindChill to set
	 */
	public void setTimeMaxWindChill(LocalTime timeMaxWindChill) {
		this.timeMaxWindChill = timeMaxWindChill;
	}

	/**
	 * @return the maxDewPoint
	 */
	public Double getMaxDewPoint() {
		return maxDewPoint;
	}

	/**
	 * @param maxDewPoint
	 *            the maxDewPoint to set
	 */
	public void setMaxDewPoint(Double maxDewPoint) {
		this.maxDewPoint = maxDewPoint;
	}

	/**
	 * @return the timeMaxDewPoint
	 */
	public LocalTime getTimeMaxDewPoint() {
		return timeMaxDewPoint;
	}

	/**
	 * @param timeMaxDewPoint
	 *            the timeMaxDewPoint to set
	 */
	public void setTimeMaxDewPoint(LocalTime timeMaxDewPoint) {
		this.timeMaxDewPoint = timeMaxDewPoint;
	}

	/**
	 * @return the maxOutHum
	 */
	public Integer getMaxOutHum() {
		return maxOutHum;
	}

	/**
	 * @param maxOutHum
	 *            the maxOutHum to set
	 */
	public void setMaxOutHum(Integer maxOutHum) {
		this.maxOutHum = maxOutHum;
	}

	/**
	 * @return the timeMaxOutHum
	 */
	public LocalTime getTimeMaxOutHum() {
		return timeMaxOutHum;
	}

	/**
	 * @param timeMaxOutHum
	 *            the timeMaxOutHum to set
	 */
	public void setTimeMaxOutHum(LocalTime timeMaxOutHum) {
		this.timeMaxOutHum = timeMaxOutHum;
	}

	/**
	 * @return the maxInHum
	 */
	public Integer getMaxInHum() {
		return maxInHum;
	}

	/**
	 * @param maxInHum
	 *            the maxInHum to set
	 */
	public void setMaxInHum(Integer maxInHum) {
		this.maxInHum = maxInHum;
	}

	/**
	 * @return the timeMaxInHum
	 */
	public LocalTime getTimeMaxInHum() {
		return timeMaxInHum;
	}

	/**
	 * @param timeMaxInHum
	 *            the timeMaxInHum to set
	 */
	public void setTimeMaxInHum(LocalTime timeMaxInHum) {
		this.timeMaxInHum = timeMaxInHum;
	}

	/**
	 * @return the maxPressure
	 */
	public Double getMaxPressure() {
		return maxPressure;
	}

	/**
	 * @param maxPressure
	 *            the maxPressure to set
	 */
	public void setMaxPressure(Double maxPressure) {
		this.maxPressure = maxPressure;
	}

	/**
	 * @return the timeMaxPressure
	 */
	public LocalTime getTimeMaxPressure() {
		return timeMaxPressure;
	}

	/**
	 * @param timeMaxPressure
	 *            the timeMaxPressure to set
	 */
	public void setTimeMaxPressure(LocalTime timeMaxPressure) {
		this.timeMaxPressure = timeMaxPressure;
	}

	/**
	 * @return the maxWindSpeed
	 */
	public Double getMaxWindSpeed() {
		return maxWindSpeed;
	}

	/**
	 * @param maxWindSpeed
	 *            the maxWindSpeed to set
	 */
	public void setMaxWindSpeed(Double maxWindSpeed) {
		this.maxWindSpeed = maxWindSpeed;
	}

	/**
	 * @return the maxWindSpeedDir
	 */
	public Double getMaxWindSpeedDir() {
		return maxWindSpeedDir;
	}

	/**
	 * @param maxWindSpeedDir
	 *            the maxWindSpeedDir to set
	 */
	public void setMaxWindSpeedDir(Double maxWindSpeedDir) {
		this.maxWindSpeedDir = maxWindSpeedDir;
	}

	/**
	 * @return the timeMaxWindSpeed
	 */
	public LocalTime getTimeMaxWindSpeed() {
		return timeMaxWindSpeed;
	}

	/**
	 * @param timeMaxWindSpeed
	 *            the timeMaxWindSpeed to set
	 */
	public void setTimeMaxWindSpeed(LocalTime timeMaxWindSpeed) {
		this.timeMaxWindSpeed = timeMaxWindSpeed;
	}

	/**
	 * @return the maxAvg10MinWindSpeed
	 */
	public Double getMaxAvg10MinWindSpeed() {
		return maxAvg10MinWindSpeed;
	}

	/**
	 * @param maxAvg10MinWindSpeed
	 *            the maxAvg10MinWindSpeed to set
	 */
	public void setMaxAvg10MinWindSpeed(Double maxAvg10MinWindSpeed) {
		this.maxAvg10MinWindSpeed = maxAvg10MinWindSpeed;
	}

	/**
	 * @return the maxAvg10MinWindSpeedDir
	 */
	public Double getMaxAvg10MinWindSpeedDir() {
		return maxAvg10MinWindSpeedDir;
	}

	/**
	 * @param maxAvg10MinWindSpeedDir
	 *            the maxAvg10MinWindSpeedDir to set
	 */
	public void setMaxAvg10MinWindSpeedDir(Double maxAvg10MinWindSpeedDir) {
		this.maxAvg10MinWindSpeedDir = maxAvg10MinWindSpeedDir;
	}

	/**
	 * @return the timeMaxAvg10MinWindSpeed
	 */
	public LocalTime getTimeMaxAvg10MinWindSpeed() {
		return timeMaxAvg10MinWindSpeed;
	}

	/**
	 * @param timeMaxAvg10MinWindSpeed
	 *            the timeMaxAvg10MinWindSpeed to set
	 */
	public void setTimeMaxAvg10MinWindSpeed(LocalTime timeMaxAvg10MinWindSpeed) {
		this.timeMaxAvg10MinWindSpeed = timeMaxAvg10MinWindSpeed;
	}

	/**
	 * @return the maxRainRate
	 */
	public Double getMaxRainRate() {
		return maxRainRate;
	}

	/**
	 * @param maxRainRate
	 *            the maxRainRate to set
	 */
	public void setMaxRainRate(Double maxRainRate) {
		this.maxRainRate = maxRainRate;
	}

	/**
	 * @return the timeMaxRainRate
	 */
	public LocalTime getTimeMaxRainRate() {
		return timeMaxRainRate;
	}

	/**
	 * @param timeMaxRainRate
	 *            the timeMaxRainRate to set
	 */
	public void setTimeMaxRainRate(LocalTime timeMaxRainRate) {
		this.timeMaxRainRate = timeMaxRainRate;
	}

	/**
	 * @return the maxUV
	 */
	public Double getMaxUV() {
		return maxUV;
	}

	/**
	 * @param maxUV
	 *            the maxUV to set
	 */
	public void setMaxUV(Double maxUV) {
		this.maxUV = maxUV;
	}

	/**
	 * @return the timeMaxUV
	 */
	public LocalTime getTimeMaxUV() {
		return timeMaxUV;
	}

	/**
	 * @param timeMaxUV
	 *            the timeMaxUV to set
	 */
	public void setTimeMaxUV(LocalTime timeMaxUV) {
		this.timeMaxUV = timeMaxUV;
	}

	/**
	 * @return the maxSolar
	 */
	public Double getMaxSolar() {
		return maxSolar;
	}

	/**
	 * @param maxSolar
	 *            the maxSolar to set
	 */
	public void setMaxSolar(Double maxSolar) {
		this.maxSolar = maxSolar;
	}

	/**
	 * @return the timeMaxSolar
	 */
	public LocalTime getTimeMaxSolar() {
		return timeMaxSolar;
	}

	/**
	 * @param timeMaxSolar
	 *            the timeMaxSolar to set
	 */
	public void setTimeMaxSolar(LocalTime timeMaxSolar) {
		this.timeMaxSolar = timeMaxSolar;
	}

	/**
	 * @return the maxHeatIndex
	 */
	public Double getMaxHeatIndex() {
		return maxHeatIndex;
	}

	/**
	 * @param maxHeatIndex
	 *            the maxHeatIndex to set
	 */
	public void setMaxHeatIndex(Double maxHeatIndex) {
		this.maxHeatIndex = maxHeatIndex;
	}

	/**
	 * @return the timeMaxHeatIndex
	 */
	public LocalTime getTimeMaxHeatIndex() {
		return timeMaxHeatIndex;
	}

	/**
	 * @param timeMaxHeatIndex
	 *            the timeMaxHeatIndex to set
	 */
	public void setTimeMaxHeatIndex(LocalTime timeMaxHeatIndex) {
		this.timeMaxHeatIndex = timeMaxHeatIndex;
	}

	/**
	 * @return the maxTHSWIndex
	 */
	public Double getMaxTHSWIndex() {
		return maxTHSWIndex;
	}

	/**
	 * @param maxTHSWIndex
	 *            the maxTHSWIndex to set
	 */
	public void setMaxTHSWIndex(Double maxTHSWIndex) {
		this.maxTHSWIndex = maxTHSWIndex;
	}

	/**
	 * @return the timeMaxTHSWIndex
	 */
	public LocalTime getTimeMaxTHSWIndex() {
		return timeMaxTHSWIndex;
	}

	/**
	 * @param timeMaxTHSWIndex
	 *            the timeMaxTHSWIndex to set
	 */
	public void setTimeMaxTHSWIndex(LocalTime timeMaxTHSWIndex) {
		this.timeMaxTHSWIndex = timeMaxTHSWIndex;
	}

	/**
	 * @return the maxTHWIndex
	 */
	public Double getMaxTHWIndex() {
		return maxTHWIndex;
	}

	/**
	 * @param maxTHWIndex
	 *            the maxTHWIndex to set
	 */
	public void setMaxTHWIndex(Double maxTHWIndex) {
		this.maxTHWIndex = maxTHWIndex;
	}

	/**
	 * @return the timeMaxTHWIndex
	 */
	public LocalTime getTimeMaxTHWIndex() {
		return timeMaxTHWIndex;
	}

	/**
	 * @param timeMaxTHWIndex
	 *            the timeMaxTHWIndex to set
	 */
	public void setTimeMaxTHWIndex(LocalTime timeMaxTHWIndex) {
		this.timeMaxTHWIndex = timeMaxTHWIndex;
	}

	/**
	 * @return the minOutTemp
	 */
	public Double getMinOutTemp() {
		return minOutTemp;
	}

	/**
	 * @param minOutTemp
	 *            the minOutTemp to set
	 */
	public void setMinOutTemp(Double minOutTemp) {
		this.minOutTemp = minOutTemp;
	}

	/**
	 * @return the timeMinOutTemp
	 */
	public LocalTime getTimeMinOutTemp() {
		return timeMinOutTemp;
	}

	/**
	 * @param timeMinOutTemp
	 *            the timeMinOutTemp to set
	 */
	public void setTimeMinOutTemp(LocalTime timeMinOutTemp) {
		this.timeMinOutTemp = timeMinOutTemp;
	}

	/**
	 * @return the minInTemp
	 */
	public Double getMinInTemp() {
		return minInTemp;
	}

	/**
	 * @param minInTemp
	 *            the minInTemp to set
	 */
	public void setMinInTemp(Double minInTemp) {
		this.minInTemp = minInTemp;
	}

	/**
	 * @return the timeMinInTemp
	 */
	public LocalTime getTimeMinInTemp() {
		return timeMinInTemp;
	}

	/**
	 * @param timeMinInTemp
	 *            the timeMinInTemp to set
	 */
	public void setTimeMinInTemp(LocalTime timeMinInTemp) {
		this.timeMinInTemp = timeMinInTemp;
	}

	/**
	 * @return the minWindChill
	 */
	public Double getMinWindChill() {
		return minWindChill;
	}

	/**
	 * @param minWindChill
	 *            the minWindChill to set
	 */
	public void setMinWindChill(Double minWindChill) {
		this.minWindChill = minWindChill;
	}

	/**
	 * @return the timeMinWindChill
	 */
	public LocalTime getTimeMinWindChill() {
		return timeMinWindChill;
	}

	/**
	 * @param timeMinWindChill
	 *            the timeMinWindChill to set
	 */
	public void setTimeMinWindChill(LocalTime timeMinWindChill) {
		this.timeMinWindChill = timeMinWindChill;
	}

	/**
	 * @return the minDewPoint
	 */
	public Double getMinDewPoint() {
		return minDewPoint;
	}

	/**
	 * @param minDewPoint
	 *            the minDewPoint to set
	 */
	public void setMinDewPoint(Double minDewPoint) {
		this.minDewPoint = minDewPoint;
	}

	/**
	 * @return the timeMinDewPoint
	 */
	public LocalTime getTimeMinDewPoint() {
		return timeMinDewPoint;
	}

	/**
	 * @param timeMinDewPoint
	 *            the timeMinDewPoint to set
	 */
	public void setTimeMinDewPoint(LocalTime timeMinDewPoint) {
		this.timeMinDewPoint = timeMinDewPoint;
	}

	/**
	 * @return the minOutHum
	 */
	public Integer getMinOutHum() {
		return minOutHum;
	}

	/**
	 * @param minOutHum
	 *            the minOutHum to set
	 */
	public void setMinOutHum(Integer minOutHum) {
		this.minOutHum = minOutHum;
	}

	/**
	 * @return the timeMinOutHum
	 */
	public LocalTime getTimeMinOutHum() {
		return timeMinOutHum;
	}

	/**
	 * @param timeMinOutHum
	 *            the timeMinOutHum to set
	 */
	public void setTimeMinOutHum(LocalTime timeMinOutHum) {
		this.timeMinOutHum = timeMinOutHum;
	}

	/**
	 * @return the minInHum
	 */
	public Integer getMinInHum() {
		return minInHum;
	}

	/**
	 * @param minInHum
	 *            the minInHum to set
	 */
	public void setMinInHum(Integer minInHum) {
		this.minInHum = minInHum;
	}

	/**
	 * @return the timeMinInHum
	 */
	public LocalTime getTimeMinInHum() {
		return timeMinInHum;
	}

	/**
	 * @param timeMinInHum
	 *            the timeMinInHum to set
	 */
	public void setTimeMinInHum(LocalTime timeMinInHum) {
		this.timeMinInHum = timeMinInHum;
	}

	/**
	 * @return the minPressure
	 */
	public Double getMinPressure() {
		return minPressure;
	}

	/**
	 * @param minPressure
	 *            the minPressure to set
	 */
	public void setMinPressure(Double minPressure) {
		this.minPressure = minPressure;
	}

	/**
	 * @return the timeMinPressure
	 */
	public LocalTime getTimeMinPressure() {
		return timeMinPressure;
	}

	/**
	 * @param timeMinPressure
	 *            the timeMinPressure to set
	 */
	public void setTimeMinPressure(LocalTime timeMinPressure) {
		this.timeMinPressure = timeMinPressure;
	}

	/**
	 * @return the minHeatIndex
	 */
	public Double getMinHeatIndex() {
		return minHeatIndex;
	}

	/**
	 * @param minHeatIndex
	 *            the minHeatIndex to set
	 */
	public void setMinHeatIndex(Double minHeatIndex) {
		this.minHeatIndex = minHeatIndex;
	}

	/**
	 * @return the timeMinHeatIndex
	 */
	public LocalTime getTimeMinHeatIndex() {
		return timeMinHeatIndex;
	}

	/**
	 * @param timeMinHeatIndex
	 *            the timeMinHeatIndex to set
	 */
	public void setTimeMinHeatIndex(LocalTime timeMinHeatIndex) {
		this.timeMinHeatIndex = timeMinHeatIndex;
	}

	/**
	 * @return the minTHSWIndex
	 */
	public Double getMinTHSWIndex() {
		return minTHSWIndex;
	}

	/**
	 * @param minTHSWIndex
	 *            the minTHSWIndex to set
	 */
	public void setMinTHSWIndex(Double minTHSWIndex) {
		this.minTHSWIndex = minTHSWIndex;
	}

	/**
	 * @return the timeMinTHSWIndex
	 */
	public LocalTime getTimeMinTHSWIndex() {
		return timeMinTHSWIndex;
	}

	/**
	 * @param timeMinTHSWIndex
	 *            the timeMinTHSWIndex to set
	 */
	public void setTimeMinTHSWIndex(LocalTime timeMinTHSWIndex) {
		this.timeMinTHSWIndex = timeMinTHSWIndex;
	}

	/**
	 * @return the minTHWIndex
	 */
	public Double getMinTHWIndex() {
		return minTHWIndex;
	}

	/**
	 * @param minTHWIndex
	 *            the minTHWIndex to set
	 */
	public void setMinTHWIndex(Double minTHWIndex) {
		this.minTHWIndex = minTHWIndex;
	}

	/**
	 * @return the timeMinTHWIndex
	 */
	public LocalTime getTimeMinTHWIndex() {
		return timeMinTHWIndex;
	}

	/**
	 * @param timeMinTHWIndex
	 *            the timeMinTHWIndex to set
	 */
	public void setTimeMinTHWIndex(LocalTime timeMinTHWIndex) {
		this.timeMinTHWIndex = timeMinTHWIndex;
	}

	/**
	 * @return the avgOutTemp
	 */
	public Double getAvgOutTemp() {
		return avgOutTemp;
	}

	/**
	 * @param avgOutTemp
	 *            the avgOutTemp to set
	 */
	public void setAvgOutTemp(Double avgOutTemp) {
		this.avgOutTemp = avgOutTemp;
	}

	/**
	 * @return the avgInTemp
	 */
	public Double getAvgInTemp() {
		return avgInTemp;
	}

	/**
	 * @param avgInTemp
	 *            the avgInTemp to set
	 */
	public void setAvgInTemp(Double avgInTemp) {
		this.avgInTemp = avgInTemp;
	}

	/**
	 * @return the avgWindChill
	 */
	public Double getAvgWindChill() {
		return avgWindChill;
	}

	/**
	 * @param avgWindChill
	 *            the avgWindChill to set
	 */
	public void setAvgWindChill(Double avgWindChill) {
		this.avgWindChill = avgWindChill;
	}

	/**
	 * @return the avgDewPoint
	 */
	public Double getAvgDewPoint() {
		return avgDewPoint;
	}

	/**
	 * @param avgDewPoint
	 *            the avgDewPoint to set
	 */
	public void setAvgDewPoint(Double avgDewPoint) {
		this.avgDewPoint = avgDewPoint;
	}

	/**
	 * @return the avgOutHum
	 */
	public Integer getAvgOutHum() {
		return avgOutHum;
	}

	/**
	 * @param avgOutHum
	 *            the avgOutHum to set
	 */
	public void setAvgOutHum(Integer avgOutHum) {
		this.avgOutHum = avgOutHum;
	}

	/**
	 * @return the avgPressure
	 */
	public Double getAvgPressure() {
		return avgPressure;
	}

	/**
	 * @param avgPressure
	 *            the avgPressure to set
	 */
	public void setAvgPressure(Double avgPressure) {
		this.avgPressure = avgPressure;
	}

	/**
	 * @return the avgWindSpeed
	 */
	public Double getAvgWindSpeed() {
		return avgWindSpeed;
	}

	/**
	 * @param avgWindSpeed
	 *            the avgWindSpeed to set
	 */
	public void setAvgWindSpeed(Double avgWindSpeed) {
		this.avgWindSpeed = avgWindSpeed;
	}

	/**
	 * @return the avgHeatIndex
	 */
	public Double getAvgHeatIndex() {
		return avgHeatIndex;
	}

	/**
	 * @param avgHeatIndex
	 *            the avgHeatIndex to set
	 */
	public void setAvgHeatIndex(Double avgHeatIndex) {
		this.avgHeatIndex = avgHeatIndex;
	}
}
