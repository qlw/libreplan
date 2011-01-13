/*
 * This file is part of NavalPlan
 *
 * Copyright (C) 2009-2010 Fundación para o Fomento da Calidade Industrial e
 *                         Desenvolvemento Tecnolóxico de Galicia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 */
package org.zkoss.ganttz.util;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.Date;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.math.Fraction;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

public class Interval {

    private final Duration lengthBetween;

    private final Days daysBetween;

    private LocalDate startInclusive;

    private LocalDate endExclusive;

    public Interval(Date start, Date finish) {
        this(LocalDate.fromDateFields(start), LocalDate.fromDateFields(finish));
    }

    public Interval(LocalDate startInclusive, LocalDate endExclusive) {
        Validate.notNull(startInclusive);
        Validate.notNull(endExclusive);
        Validate.isTrue(endExclusive.isAfter(startInclusive));
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
        this.lengthBetween = new Duration(
                this.startInclusive.toDateTimeAtStartOfDay(),
                this.endExclusive.toDateTimeAtStartOfDay());
        this.daysBetween = Days.daysBetween(this.startInclusive,
                this.endExclusive);
    }

    public Days getDaysBetween() {
        return daysBetween;
    }

    public LocalDate getStart() {
        return startInclusive;
    }

    public LocalDate getFinish() {
        return endExclusive;
    }

    public Duration getLengthBetween() {
        return lengthBetween;
    }

    public Fraction getProportion(DateTime date) {
        Days fromStartToDate = Days.daysBetween(startInclusive,
                date.toLocalDate());
        Fraction fraction = Fraction.getFraction(fromStartToDate.getDays(),
                this.daysBetween.getDays());
        return fraction.add(inTheDayIncrement(date));
    }

    private Fraction inTheDayIncrement(DateTime date) {
        DateTime atStartOfDay = date.toLocalDate().toDateTimeAtStartOfDay();
        Duration duration = new Duration(atStartOfDay, date);
        double result = ((double) duration.getMillis())
                / lengthBetween.getMillis();
        try {
            return Fraction.getFraction(result);
        } catch (ArithmeticException e) {
            return Fraction.ZERO;
        }
    }

    @SuppressWarnings("unchecked")
    public Interval coalesce(Interval otherInterval) {
        Validate.notNull(otherInterval);
        LocalDate minStart = Collections.min(asList(startInclusive,
                otherInterval.startInclusive));
        LocalDate maxEnd = Collections.max(asList(endExclusive,
                otherInterval.endExclusive));
        return new Interval(minStart, maxEnd);
    }
}