/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
 */
package org.pentaho.reporting.platform.plugin.output;

import org.pentaho.reporting.platform.plugin.SimpleReportingAction;

import java.io.IOException;
import java.io.InputStream;

public class FastExportReportOutputHandlerFactory extends DefaultReportOutputHandlerFactory {
  public FastExportReportOutputHandlerFactory() {
  }

  protected ReportOutputHandler createXlsxOutput( final ReportOutputHandlerSelector selector ) throws IOException {
    if ( isXlsxAvailable() == false ) {
      return null;
    }
    InputStream input = selector.getInput( SimpleReportingAction.XLS_WORKBOOK_PARAM, null, InputStream.class );
    if ( input != null ) {
      XLSXOutput xlsxOutput = new XLSXOutput();
      xlsxOutput.setTemplateDataFromStream( input );
      return xlsxOutput;
    }

    return new FastXLSXOutput();
  }

  protected ReportOutputHandler createXlsOutput( final ReportOutputHandlerSelector selector ) throws IOException {
    if ( isXlsxAvailable() == false ) {
      return null;
    }
    InputStream input = selector.getInput( SimpleReportingAction.XLS_WORKBOOK_PARAM, null, InputStream.class );
    if ( input != null ) {
      XLSOutput xlsOutput = new XLSOutput();
      xlsOutput.setTemplateDataFromStream( input );
      return xlsOutput;
    }

    return new FastXLSOutput();
  }

  protected ReportOutputHandler createCsvOutput() {
    if ( isCsvAvailable() == false ) {
      return null;
    }
    return new FastCSVOutput();
  }

  protected ReportOutputHandler createHtmlStreamOutput( final ReportOutputHandlerSelector selector ) {
    if ( isHtmlStreamAvailable() == false ) {
      return null;
    }
    if ( selector.isUseJcrOutput() ) {
      return new FastStreamJcrHtmlOutput( computeContentHandlerPattern( selector ), selector.getJcrOutputPath() );
    } else {
      return new FastStreamHtmlOutput( computeContentHandlerPattern( selector ) );
    }
  }
}
