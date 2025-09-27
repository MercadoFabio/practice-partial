import { Component, EventEmitter, Inject, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Product } from '../../models/product.model';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnChanges {
  @Input() product: Product | null = null;
  @Output() formSubmit = new EventEmitter<Product>();

  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA)
    public data: { product: Product },
    private dialogRef: MatDialogRef<ProductFormComponent>
  ) {
    this.productForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      descripcion: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(500)]],
      precio: ['', [Validators.required, Validators.min(0.01), Validators.max(1000000)]],
      stock: ['', [Validators.required, Validators.min(0), Validators.max(10000)]]
    });

    // If product data is provided, patch the form with its value
    console.log(this.data)
    if (this.data.product) {
      this.product = this.data.product;
      this.productForm.patchValue(this.product);
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes['data'])
    if (changes['data'] && this.product) {
      this.productForm.patchValue(this.product);
    }
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      // If we're in a dialog, close it with the form value
      if (this.data) {
        this.dialogRef.close(this.productForm.value);
      } else {
        // If not in a dialog, emit the form value
        this.formSubmit.emit(this.productForm.value);
        if (!this.product) {
          this.productForm.reset();
        }
      }
    } else {
      // Mark all fields as touched to show validation messages
      Object.keys(this.productForm.controls).forEach(key => {
        this.productForm.get(key)?.markAsTouched();
      });
    }
  }

  // Helper methods for form validation
  hasError(controlName: string, errorName: string): boolean {
    const control = this.productForm.get(controlName);
    return control ? control.hasError(errorName) && (control.dirty || control.touched) : false;
  }
}
