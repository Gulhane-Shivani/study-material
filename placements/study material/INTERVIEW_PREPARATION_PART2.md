# Frontend React Interview Preparation - Part 2

## Table of Contents (Questions 51-100)
6. [UI Libraries](#ui-libraries)
7. [Form Handling](#form-handling)
8. [API Integration](#api-integration)
9. [Authentication & Authorization](#authentication-authorization)
10. [Performance & Best Practices](#performance-best-practices)

---

## UI Libraries

### Q51: What UI libraries did we use and why multiple libraries?
**Answer:** We use multiple UI libraries for different purposes:

**1. Tailwind CSS** - Utility-first CSS framework
- Rapid development
- Consistent design system
- Small bundle size (purges unused styles)
- Responsive design utilities

**2. Material-UI (MUI)**
- Data Grid component (`@mui/x-data-grid`)
- Rich component library
- Accessibility built-in
- Professional look

**3. Mantine**
- Form management (`@mantine/form`)
- Hooks library (`@mantine/hooks`)
- Modern components

**4. Radix UI**
- Unstyled, accessible components
- Dialog, Select, Accordion
- Full control over styling
- ARIA compliant

**5. Lucide React**
- Icon library
- Tree-shakeable
- Consistent icon set

**6. Framer Motion**
- Animations
- Page transitions
- Gesture handling

### Q52: How do we use Tailwind CSS in the project?
**Answer:** Tailwind provides utility classes for styling:

**Configuration:**
```js
// tailwind.config.js
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        primary: '#3b82f6',
        secondary: '#8b5cf6',
      },
      fontFamily: {
        sans: ['Inter', 'Outfit', 'sans-serif'],
      },
    },
  },
  plugins: [require('tailwindcss-animate')],
};
```

**Usage examples:**
```tsx
// Layout
<div className="flex items-center justify-between p-4">

// Responsive
<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">

// Hover effects
<button className="bg-blue-500 hover:bg-blue-600 transition-colors">

// Dark mode
<div className="bg-white dark:bg-gray-800">

// Custom utilities
<div className="animate-fade-in">
```

### Q53: Explain the MUI DataGrid component we use.
**Answer:** MUI DataGrid is a powerful table component for displaying data.

**Basic usage:**
```tsx
import { DataGrid, GridColDef } from '@mui/x-data-grid';

const columns: GridColDef[] = [
  { field: 'id', headerName: 'ID', width: 90 },
  { field: 'name', headerName: 'Name', width: 150 },
  { field: 'email', headerName: 'Email', width: 200 },
  {
    field: 'actions',
    headerName: 'Actions',
    width: 150,
    renderCell: (params) => (
      <button onClick={() => handleEdit(params.row)}>
        Edit
      </button>
    ),
  },
];

const StudentTable = () => {
  const [students, setStudents] = useState([]);
  
  return (
    <DataGrid
      rows={students}
      columns={columns}
      pageSize={10}
      checkboxSelection
      disableSelectionOnClick
      autoHeight
    />
  );
};
```

**Features we use:**
- Sorting
- Filtering
- Pagination
- Row selection
- Custom cell rendering
- Export functionality

### Q54: How do we use Radix UI components?
**Answer:** Radix provides unstyled, accessible components:

**Dialog example:**
```tsx
import * as Dialog from '@radix-ui/react-dialog';

const DeleteConfirmDialog = ({ open, onClose, onConfirm }) => {
  return (
    <Dialog.Root open={open} onOpenChange={onClose}>
      <Dialog.Portal>
        <Dialog.Overlay className="fixed inset-0 bg-black/50" />
        <Dialog.Content className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white rounded-lg p-6">
          <Dialog.Title className="text-xl font-bold">
            Confirm Delete
          </Dialog.Title>
          <Dialog.Description className="mt-2 text-gray-600">
            Are you sure you want to delete this item?
          </Dialog.Description>
          <div className="mt-4 flex gap-2">
            <button onClick={onConfirm}>Delete</button>
            <Dialog.Close asChild>
              <button>Cancel</button>
            </Dialog.Close>
          </div>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
};
```

**Select example:**
```tsx
import * as Select from '@radix-ui/react-select';

<Select.Root value={value} onValueChange={setValue}>
  <Select.Trigger className="px-4 py-2 border rounded">
    <Select.Value placeholder="Select option" />
  </Select.Trigger>
  <Select.Portal>
    <Select.Content className="bg-white border rounded shadow-lg">
      <Select.Item value="option1">Option 1</Select.Item>
      <Select.Item value="option2">Option 2</Select.Item>
    </Select.Content>
  </Select.Portal>
</Select.Root>
```

### Q55: How do we implement animations with Framer Motion?
**Answer:** Framer Motion provides declarative animations:

**Page transitions:**
```tsx
import { motion, AnimatePresence } from 'framer-motion';

const PageTransition = ({ children }) => {
  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      exit={{ opacity: 0, y: -20 }}
      transition={{ duration: 0.3 }}
    >
      {children}
    </motion.div>
  );
};
```

**List animations:**
```tsx
<motion.div
  initial={{ opacity: 0 }}
  animate={{ opacity: 1 }}
  transition={{ staggerChildren: 0.1 }}
>
  {items.map((item, index) => (
    <motion.div
      key={item.id}
      initial={{ opacity: 0, x: -20 }}
      animate={{ opacity: 1, x: 0 }}
      transition={{ delay: index * 0.1 }}
    >
      {item.name}
    </motion.div>
  ))}
</motion.div>
```

**Hover effects:**
```tsx
<motion.button
  whileHover={{ scale: 1.05 }}
  whileTap={{ scale: 0.95 }}
  className="px-4 py-2 bg-blue-500 text-white rounded"
>
  Click Me
</motion.button>
```

### Q56: What is the clsx/tailwind-merge pattern we use?
**Answer:** We use `clsx` and `tailwind-merge` for conditional class names:

**Utility function:**
```tsx
import { clsx, type ClassValue } from 'clsx';
import { twMerge } from 'tailwind-merge';

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}
```

**Usage:**
```tsx
// Conditional classes
<div className={cn(
  'px-4 py-2 rounded',
  isActive && 'bg-blue-500 text-white',
  isDisabled && 'opacity-50 cursor-not-allowed'
)} />

// Merging classes (tailwind-merge handles conflicts)
<Button className={cn('bg-blue-500', className)} />
// If className="bg-red-500", result is "bg-red-500" (not both)
```

### Q57: How do we ensure responsive design?
**Answer:** Multiple approaches for responsive design:

**1. Tailwind responsive utilities:**
```tsx
<div className="
  grid 
  grid-cols-1 
  sm:grid-cols-2 
  md:grid-cols-3 
  lg:grid-cols-4 
  gap-4
">
```

**2. Container queries:**
```tsx
<div className="container mx-auto px-4 sm:px-6 lg:px-8">
```

**3. Responsive typography:**
```tsx
<h1 className="text-2xl sm:text-3xl md:text-4xl lg:text-5xl">
```

**4. Hide/show on breakpoints:**
```tsx
<div className="hidden md:block">Desktop only</div>
<div className="block md:hidden">Mobile only</div>
```

**5. Flexbox/Grid:**
```tsx
<div className="flex flex-col md:flex-row">
```

### Q58: What accessibility features did we implement?
**Answer:** Accessibility is built into our components:

**1. Semantic HTML:**
```tsx
<nav>, <main>, <article>, <section>, <header>, <footer>
```

**2. ARIA labels:**
```tsx
<button aria-label="Close dialog">×</button>
<input aria-describedby="email-error" />
```

**3. Keyboard navigation:**
```tsx
<div 
  role="button"
  tabIndex={0}
  onKeyDown={(e) => e.key === 'Enter' && handleClick()}
>
```

**4. Focus management:**
```tsx
const buttonRef = useRef<HTMLButtonElement>(null);

useEffect(() => {
  buttonRef.current?.focus();
}, []);
```

**5. Color contrast:**
- Ensure text meets WCAG standards
- Use Tailwind's color palette

**6. Screen reader support:**
```tsx
<span className="sr-only">Loading...</span>
```

### Q59: How do we handle icons in the project?
**Answer:** We use Lucide React for icons:

**Import and usage:**
```tsx
import { 
  User, 
  Mail, 
  Lock, 
  Search, 
  Edit, 
  Trash2,
  ChevronDown 
} from 'lucide-react';

const LoginForm = () => {
  return (
    <div>
      <Mail size={20} className="text-gray-500" />
      <input type="email" placeholder="Email" />
    </div>
  );
};
```

**Dynamic icons:**
```tsx
const iconMap = {
  user: User,
  mail: Mail,
  lock: Lock,
};

const DynamicIcon = ({ name, ...props }) => {
  const Icon = iconMap[name];
  return <Icon {...props} />;
};
```

**Benefits:**
- Tree-shakeable (only imports used icons)
- Consistent design
- Customizable size and color
- TypeScript support

### Q60: What is the shadcn/ui pattern we follow?
**Answer:** We follow shadcn/ui's component pattern:

**Principles:**
1. **Copy, don't install**: Components are in our codebase
2. **Customizable**: Full control over styling
3. **Accessible**: Built on Radix UI
4. **Type-safe**: Full TypeScript support

**Example Button component:**
```tsx
// components/ui/Button.tsx
import { cn } from '@/lib/utils';
import { cva, type VariantProps } from 'class-variance-authority';

const buttonVariants = cva(
  'inline-flex items-center justify-center rounded-md font-medium transition-colors',
  {
    variants: {
      variant: {
        default: 'bg-blue-500 text-white hover:bg-blue-600',
        outline: 'border border-gray-300 hover:bg-gray-100',
        ghost: 'hover:bg-gray-100',
      },
      size: {
        sm: 'h-8 px-3 text-sm',
        md: 'h-10 px-4',
        lg: 'h-12 px-6 text-lg',
      },
    },
    defaultVariants: {
      variant: 'default',
      size: 'md',
    },
  }
);

interface ButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement>,
    VariantProps<typeof buttonVariants> {}

export const Button = ({ 
  className, 
  variant, 
  size, 
  ...props 
}: ButtonProps) => {
  return (
    <button
      className={cn(buttonVariants({ variant, size, className }))}
      {...props}
    />
  );
};

// Usage
<Button variant="outline" size="sm">Click</Button>
```

---

## Form Handling

### Q61: What form libraries do we use?
**Answer:** We use multiple form handling approaches:

**1. React Hook Form** - Primary form library
```tsx
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';

const schema = z.object({
  email: z.string().email(),
  password: z.string().min(8),
});

type FormData = z.infer<typeof schema>;

const LoginForm = () => {
  const { register, handleSubmit, formState: { errors } } = useForm<FormData>({
    resolver: zodResolver(schema),
  });
  
  const onSubmit = (data: FormData) => {
    console.log(data);
  };
  
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input {...register('email')} />
      {errors.email && <span>{errors.email.message}</span>}
      
      <input type="password" {...register('password')} />
      {errors.password && <span>{errors.password.message}</span>}
      
      <button type="submit">Login</button>
    </form>
  );
};
```

**2. Mantine Form** - For complex forms
**3. Controlled components** - Simple forms

### Q62: How does React Hook Form work?
**Answer:** React Hook Form uses uncontrolled components with refs for better performance:

**Key concepts:**

**1. Register inputs:**
```tsx
const { register } = useForm();
<input {...register('fieldName')} />
// Spreads: name, ref, onChange, onBlur
```

**2. Validation:**
```tsx
<input {...register('email', {
  required: 'Email is required',
  pattern: {
    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
    message: 'Invalid email'
  }
})} />
```

**3. Form state:**
```tsx
const { formState: { errors, isSubmitting, isDirty, isValid } } = useForm();
```

**4. Watch values:**
```tsx
const password = watch('password');
```

**5. Set values programmatically:**
```tsx
const { setValue } = useForm();
setValue('email', 'test@example.com');
```

**6. Reset form:**
```tsx
const { reset } = useForm();
reset(); // Clear all fields
```

### Q63: What is Zod and how do we use it for validation?
**Answer:** Zod is a TypeScript-first schema validation library:

**Schema definition:**
```tsx
import { z } from 'zod';

const internshipSchema = z.object({
  title: z.string().min(5, 'Title must be at least 5 characters'),
  companyName: z.string().min(1, 'Company name is required'),
  description: z.string().max(500, 'Description too long'),
  stipend: z.number().min(0).max(100000),
  duration: z.enum(['1-month', '2-months', '3-months', '6-months']),
  location: z.string(),
  requirements: z.array(z.string()).min(1),
  deadline: z.date().min(new Date(), 'Deadline must be in future'),
  email: z.string().email('Invalid email'),
  website: z.string().url('Invalid URL').optional(),
});

type InternshipFormData = z.infer<typeof internshipSchema>;
```

**With React Hook Form:**
```tsx
import { zodResolver } from '@hookform/resolvers/zod';

const { register, handleSubmit, formState: { errors } } = useForm<InternshipFormData>({
  resolver: zodResolver(internshipSchema),
});
```

**Custom validation:**
```tsx
const passwordSchema = z.string()
  .min(8)
  .regex(/[A-Z]/, 'Must contain uppercase')
  .regex(/[a-z]/, 'Must contain lowercase')
  .regex(/[0-9]/, 'Must contain number');

const signupSchema = z.object({
  password: passwordSchema,
  confirmPassword: z.string(),
}).refine(data => data.password === data.confirmPassword, {
  message: "Passwords don't match",
  path: ['confirmPassword'],
});
```

### Q64: How do we handle file uploads?
**Answer:** Multiple approaches for file handling:

**1. React Dropzone:**
```tsx
import { useDropzone } from 'react-dropzone';

const ResumeUpload = () => {
  const onDrop = useCallback((acceptedFiles: File[]) => {
    const file = acceptedFiles[0];
    // Handle file
  }, []);
  
  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
    accept: {
      'application/pdf': ['.pdf'],
      'application/msword': ['.doc', '.docx'],
    },
    maxSize: 5 * 1024 * 1024, // 5MB
    multiple: false,
  });
  
  return (
    <div {...getRootProps()} className={cn(
      'border-2 border-dashed rounded-lg p-8',
      isDragActive && 'border-blue-500 bg-blue-50'
    )}>
      <input {...getInputProps()} />
      <p>Drag & drop resume, or click to select</p>
    </div>
  );
};
```

**2. With React Hook Form:**
```tsx
const { register } = useForm();

<input 
  type="file"
  {...register('resume')}
  accept=".pdf,.doc,.docx"
/>
```

**3. FormData for API:**
```tsx
const handleSubmit = async (data: FormData) => {
  const formData = new FormData();
  formData.append('resume', data.resume[0]);
  formData.append('name', data.name);
  
  await axios.post('/api/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
};
```

### Q65: How do we handle form errors and display them?
**Answer:** Multiple error handling strategies:

**1. Field-level errors:**
```tsx
const { register, formState: { errors } } = useForm();

<div>
  <input {...register('email')} />
  {errors.email && (
    <span className="text-red-500 text-sm">
      {errors.email.message}
    </span>
  )}
</div>
```

**2. Error component:**
```tsx
const FormError = ({ error }: { error?: FieldError }) => {
  if (!error) return null;
  
  return (
    <div className="flex items-center gap-1 text-red-500 text-sm mt-1">
      <AlertCircle size={16} />
      <span>{error.message}</span>
    </div>
  );
};

// Usage
<FormError error={errors.email} />
```

**3. Form-level errors:**
```tsx
const { setError } = useForm();

try {
  await submitForm(data);
} catch (error) {
  setError('root', {
    message: 'Failed to submit form. Please try again.',
  });
}

{errors.root && (
  <div className="bg-red-50 border border-red-200 rounded p-4">
    {errors.root.message}
  </div>
)}
```

**4. Toast notifications:**
```tsx
import { toast } from 'react-hot-toast';

const onSubmit = async (data) => {
  try {
    await api.post('/submit', data);
    toast.success('Form submitted successfully!');
  } catch (error) {
    toast.error('Failed to submit form');
  }
};
```

### Q66: How do we implement multi-step forms?
**Answer:** Multi-step forms with state management:

```tsx
const MultiStepForm = () => {
  const [step, setStep] = useState(1);
  const [formData, setFormData] = useState({});
  
  const { register, handleSubmit, formState: { errors } } = useForm();
  
  const onStepSubmit = (data: any) => {
    setFormData(prev => ({ ...prev, ...data }));
    if (step < 3) {
      setStep(step + 1);
    } else {
      // Final submission
      submitCompleteForm({ ...formData, ...data });
    }
  };
  
  return (
    <div>
      {/* Progress indicator */}
      <div className="flex gap-2 mb-8">
        {[1, 2, 3].map(s => (
          <div key={s} className={cn(
            'h-2 flex-1 rounded',
            s <= step ? 'bg-blue-500' : 'bg-gray-200'
          )} />
        ))}
      </div>
      
      <form onSubmit={handleSubmit(onStepSubmit)}>
        {step === 1 && <PersonalInfo register={register} errors={errors} />}
        {step === 2 && <EducationInfo register={register} errors={errors} />}
        {step === 3 && <ExperienceInfo register={register} errors={errors} />}
        
        <div className="flex gap-2 mt-4">
          {step > 1 && (
            <button type="button" onClick={() => setStep(step - 1)}>
              Previous
            </button>
          )}
          <button type="submit">
            {step < 3 ? 'Next' : 'Submit'}
          </button>
        </div>
      </form>
    </div>
  );
};
```

### Q67: How do we handle dynamic form fields?
**Answer:** Using useFieldArray from React Hook Form:

```tsx
import { useForm, useFieldArray } from 'react-hook-form';

const DynamicForm = () => {
  const { register, control } = useForm({
    defaultValues: {
      skills: [{ name: '', level: '' }],
    },
  });
  
  const { fields, append, remove } = useFieldArray({
    control,
    name: 'skills',
  });
  
  return (
    <form>
      {fields.map((field, index) => (
        <div key={field.id} className="flex gap-2">
          <input {...register(`skills.${index}.name`)} placeholder="Skill" />
          <select {...register(`skills.${index}.level`)}>
            <option value="beginner">Beginner</option>
            <option value="intermediate">Intermediate</option>
            <option value="expert">Expert</option>
          </select>
          <button type="button" onClick={() => remove(index)}>
            Remove
          </button>
        </div>
      ))}
      
      <button 
        type="button" 
        onClick={() => append({ name: '', level: '' })}
      >
        Add Skill
      </button>
    </form>
  );
};
```

### Q68: How do we implement form auto-save?
**Answer:** Using watch and debounce:

```tsx
import { useForm } from 'react-hook-form';
import { useEffect } from 'react';
import { debounce } from 'lodash';

const AutoSaveForm = () => {
  const { register, watch } = useForm();
  const formData = watch();
  
  useEffect(() => {
    const saveForm = debounce(async (data) => {
      await axios.post('/api/save-draft', data);
      toast.success('Draft saved');
    }, 1000);
    
    saveForm(formData);
    
    return () => saveForm.cancel();
  }, [formData]);
  
  return (
    <form>
      <input {...register('title')} />
      <textarea {...register('description')} />
    </form>
  );
};
```

### Q69: How do we handle select dropdowns with search?
**Answer:** Custom select component with filtering:

```tsx
import { useState } from 'react';
import * as Select from '@radix-ui/react-select';

const SearchableSelect = ({ options, value, onChange }) => {
  const [search, setSearch] = useState('');
  
  const filteredOptions = options.filter(opt =>
    opt.label.toLowerCase().includes(search.toLowerCase())
  );
  
  return (
    <Select.Root value={value} onValueChange={onChange}>
      <Select.Trigger>
        <Select.Value placeholder="Select..." />
      </Select.Trigger>
      <Select.Portal>
        <Select.Content>
          <input
            type="text"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search..."
            className="w-full p-2 border-b"
          />
          <Select.Viewport>
            {filteredOptions.map(opt => (
              <Select.Item key={opt.value} value={opt.value}>
                <Select.ItemText>{opt.label}</Select.ItemText>
              </Select.Item>
            ))}
          </Select.Viewport>
        </Select.Content>
      </Select.Portal>
    </Select.Root>
  );
};
```

### Q70: How do we implement form field dependencies?
**Answer:** Fields that depend on other field values:

```tsx
const { register, watch } = useForm();
const internshipType = watch('type');

<form>
  <select {...register('type')}>
    <option value="paid">Paid</option>
    <option value="unpaid">Unpaid</option>
  </select>
  
  {internshipType === 'paid' && (
    <input 
      type="number"
      {...register('stipend', { required: true })}
      placeholder="Stipend amount"
    />
  )}
</form>
```

---

## API Integration

### Q71: How do we configure Axios in the project?
**Answer:** We create an Axios instance with default configuration:

```tsx
// src/utils/Api/axiosConfig.ts
import axios from 'axios';
import Cookies from 'js-cookie';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor
api.interceptors.request.use(
  (config) => {
    const token = Cookies.get('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Redirect to login
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
```

### Q72: How do we handle API errors?
**Answer:** Centralized error handling:

```tsx
// Error handler utility
export const handleApiError = (error: any) => {
  if (axios.isAxiosError(error)) {
    if (error.response) {
      // Server responded with error
      const message = error.response.data?.message || 'An error occurred';
      toast.error(message);
      return message;
    } else if (error.request) {
      // Request made but no response
      toast.error('Network error. Please check your connection.');
      return 'Network error';
    }
  }
  toast.error('An unexpected error occurred');
  return 'Unexpected error';
};

// Usage in components
const fetchData = async () => {
  try {
    const response = await api.get('/internships');
    setData(response.data);
  } catch (error) {
    handleApiError(error);
  }
};
```

### Q73: How do we implement loading states?
**Answer:** Multiple patterns for loading states:

**1. Component-level:**
```tsx
const InternshipList = () => {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);
  
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await api.get('/internships');
        setData(response.data);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);
  
  if (loading) return <Spinner />;
  
  return <div>{/* Render data */}</div>;
};
```

**2. Global loading (Jotai):**
```tsx
const loadingAtom = atom(false);

const useApi = () => {
  const setLoading = useSetAtom(loadingAtom);
  
  const get = async (url: string) => {
    setLoading(true);
    try {
      return await api.get(url);
    } finally {
      setLoading(false);
    }
  };
  
  return { get };
};
```

**3. Skeleton loading:**
```tsx
const SkeletonCard = () => (
  <div className="animate-pulse">
    <div className="h-4 bg-gray-200 rounded w-3/4 mb-2" />
    <div className="h-4 bg-gray-200 rounded w-1/2" />
  </div>
);

{loading ? <SkeletonCard /> : <ActualCard />}
```

### Q74: How do we implement data fetching with caching?
**Answer:** Custom hook with caching:

```tsx
const cache = new Map();

const useFetch = <T,>(url: string) => {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  
  useEffect(() => {
    const fetchData = async () => {
      // Check cache
      if (cache.has(url)) {
        setData(cache.get(url));
        return;
      }
      
      setLoading(true);
      try {
        const response = await api.get<T>(url);
        cache.set(url, response.data);
        setData(response.data);
      } catch (err) {
        setError(handleApiError(err));
      } finally {
        setLoading(false);
      }
    };
    
    fetchData();
  }, [url]);
  
  return { data, loading, error };
};

// Usage
const { data, loading, error } = useFetch<Internship[]>('/api/internships');
```

### Q75: How do we handle pagination?
**Answer:** Pagination implementation:

```tsx
const InternshipList = () => {
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [data, setData] = useState([]);
  const pageSize = 10;
  
  useEffect(() => {
    const fetchData = async () => {
      const response = await api.get('/internships', {
        params: { page, size: pageSize },
      });
      setData(response.data.content);
      setTotalPages(response.data.totalPages);
    };
    fetchData();
  }, [page]);
  
  return (
    <div>
      {data.map(item => <Card key={item.id} data={item} />)}
      
      <div className="flex gap-2 mt-4">
        <button 
          onClick={() => setPage(p => Math.max(1, p - 1))}
          disabled={page === 1}
        >
          Previous
        </button>
        <span>Page {page} of {totalPages}</span>
        <button 
          onClick={() => setPage(p => Math.min(totalPages, p + 1))}
          disabled={page === totalPages}
        >
          Next
        </button>
      </div>
    </div>
  );
};
```

### Q76: How do we implement search and filtering?
**Answer:** Search with debouncing:

```tsx
import { useState, useEffect } from 'react';
import { debounce } from 'lodash';

const SearchableList = () => {
  const [search, setSearch] = useState('');
  const [filters, setFilters] = useState({ category: 'all', status: 'all' });
  const [results, setResults] = useState([]);
  
  useEffect(() => {
    const searchData = debounce(async () => {
      const response = await api.get('/internships/search', {
        params: { 
          query: search,
          category: filters.category,
          status: filters.status,
        },
      });
      setResults(response.data);
    }, 500);
    
    searchData();
    return () => searchData.cancel();
  }, [search, filters]);
  
  return (
    <div>
      <input
        type="text"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        placeholder="Search internships..."
      />
      
      <select 
        value={filters.category}
        onChange={(e) => setFilters(f => ({ ...f, category: e.target.value }))}
      >
        <option value="all">All Categories</option>
        <option value="internship">Internship</option>
        <option value="placement">Placement</option>
      </select>
      
      {results.map(item => <Card key={item.id} data={item} />)}
    </div>
  );
};
```

### Q77: How do we handle file downloads?
**Answer:** Download implementation:

```tsx
const handleDownload = async (fileId: number, filename: string) => {
  try {
    const response = await api.get(`/files/${fileId}`, {
      responseType: 'blob',
    });
    
    // Create blob URL
    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    link.remove();
    window.URL.revokeObjectURL(url);
    
    toast.success('File downloaded successfully');
  } catch (error) {
    toast.error('Failed to download file');
  }
};

// Usage
<button onClick={() => handleDownload(resumeId, 'resume.pdf')}>
  Download Resume
</button>
```

### Q78: How do we implement optimistic updates?
**Answer:** Update UI before API confirmation:

```tsx
const handleLike = async (postId: number) => {
  // Optimistic update
  setPosts(posts.map(post => 
    post.id === postId 
      ? { ...post, liked: true, likes: post.likes + 1 }
      : post
  ));
  
  try {
    await api.post(`/posts/${postId}/like`);
  } catch (error) {
    // Revert on error
    setPosts(posts.map(post => 
      post.id === postId 
        ? { ...post, liked: false, likes: post.likes - 1 }
        : post
    ));
    toast.error('Failed to like post');
  }
};
```

### Q79: How do we handle concurrent API requests?
**Answer:** Using Promise.all:

```tsx
const fetchDashboardData = async () => {
  setLoading(true);
  try {
    const [internships, applications, profile] = await Promise.all([
      api.get('/internships'),
      api.get('/applications'),
      api.get('/profile'),
    ]);
    
    setInternships(internships.data);
    setApplications(applications.data);
    setProfile(profile.data);
  } catch (error) {
    handleApiError(error);
  } finally {
    setLoading(false);
  }
};
```

### Q80: How do we implement retry logic?
**Answer:** Retry failed requests:

```tsx
const fetchWithRetry = async (url: string, retries = 3) => {
  for (let i = 0; i < retries; i++) {
    try {
      return await api.get(url);
    } catch (error) {
      if (i === retries - 1) throw error;
      await new Promise(resolve => setTimeout(resolve, 1000 * (i + 1)));
    }
  }
};

// Usage
const data = await fetchWithRetry('/api/internships');
```

---

## Authentication & Authorization

### Q81: How do we implement JWT authentication?
**Answer:** JWT-based auth flow:

```tsx
// Login
const login = async (email: string, password: string) => {
  const response = await api.post('/auth/login', { email, password });
  const { token, user } = response.data;
  
  // Store token
  Cookies.set('token', token, { expires: 7 });
  
  // Decode and store user
  const decoded = jwtDecode<User>(token);
  setUser(decoded);
  
  navigate('/dashboard');
};

// Logout
const logout = () => {
  Cookies.remove('token');
  setUser(null);
  navigate('/login');
};

// Check auth on app load
useEffect(() => {
  const token = Cookies.get('token');
  if (token) {
    try {
      const decoded = jwtDecode<User>(token);
      setUser(decoded);
    } catch {
      logout();
    }
  }
}, []);
```

### Q82: How do we protect routes based on user roles?
**Answer:** Role-based route protection:

```tsx
interface PrivateRouteProps {
  element: React.ReactElement;
  allowedRoles: string[];
}

const PrivateRoute = ({ element, allowedRoles }: PrivateRouteProps) => {
  const [user] = useAtom(userAtom);
  const navigate = useNavigate();
  
  useEffect(() => {
    if (!user) {
      navigate('/login', { state: { from: location.pathname } });
      return;
    }
    
    if (!allowedRoles.includes(user.role)) {
      navigate('/unauthorized');
    }
  }, [user, allowedRoles]);
  
  if (!user || !allowedRoles.includes(user.role)) {
    return null;
  }
  
  return element;
};

// Route configuration
{
  path: 'admin/*',
  element: <PrivateRoute 
    element={<AdminLayout />} 
    allowedRoles={['ADMIN']} 
  />
}
```

### Q83: How do we handle token refresh?
**Answer:** Automatic token refresh:

```tsx
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      
      try {
        const refreshToken = Cookies.get('refreshToken');
        const response = await axios.post('/auth/refresh', { refreshToken });
        const { token } = response.data;
        
        Cookies.set('token', token);
        originalRequest.headers.Authorization = `Bearer ${token}`;
        
        return api(originalRequest);
      } catch (refreshError) {
        // Refresh failed, logout
        logout();
        return Promise.reject(refreshError);
      }
    }
    
    return Promise.reject(error);
  }
);
```

### Q84: How do we implement role-based UI rendering?
**Answer:** Conditional rendering based on roles:

```tsx
const Dashboard = () => {
  const [user] = useAtom(userAtom);
  
  return (
    <div>
      <h1>Dashboard</h1>
      
      {user?.role === 'STUDENT' && <StudentDashboard />}
      {user?.role === 'TEACHER' && <TeacherDashboard />}
      {user?.role === 'ADMIN' && <AdminDashboard />}
      
      {/* Conditional features */}
      {['ADMIN', 'TEACHER'].includes(user?.role) && (
        <button>Create Internship</button>
      )}
    </div>
  );
};

// Reusable component
const RoleGuard = ({ 
  allowedRoles, 
  children 
}: { 
  allowedRoles: string[]; 
  children: React.ReactNode;
}) => {
  const [user] = useAtom(userAtom);
  
  if (!user || !allowedRoles.includes(user.role)) {
    return null;
  }
  
  return <>{children}</>;
};

// Usage
<RoleGuard allowedRoles={['ADMIN']}>
  <AdminPanel />
</RoleGuard>
```

### Q85: How do we handle session timeout?
**Answer:** Automatic logout on inactivity:

```tsx
const useSessionTimeout = (timeoutMinutes = 30) => {
  const logout = useSetAtom(logoutAtom);
  const timeoutRef = useRef<NodeJS.Timeout>();
  
  const resetTimeout = useCallback(() => {
    if (timeoutRef.current) {
      clearTimeout(timeoutRef.current);
    }
    
    timeoutRef.current = setTimeout(() => {
      logout();
      toast.error('Session expired. Please login again.');
    }, timeoutMinutes * 60 * 1000);
  }, [timeoutMinutes, logout]);
  
  useEffect(() => {
    // Reset on user activity
    const events = ['mousedown', 'keydown', 'scroll', 'touchstart'];
    
    events.forEach(event => {
      document.addEventListener(event, resetTimeout);
    });
    
    resetTimeout();
    
    return () => {
      events.forEach(event => {
        document.removeEventListener(event, resetTimeout);
      });
      if (timeoutRef.current) {
        clearTimeout(timeoutRef.current);
      }
    };
  }, [resetTimeout]);
};
```

### Q86: How do we implement "Remember Me" functionality?
**Answer:** Persistent vs session storage:

```tsx
const login = async (email: string, password: string, rememberMe: boolean) => {
  const response = await api.post('/auth/login', { email, password });
  const { token } = response.data;
  
  if (rememberMe) {
    // Persistent cookie (7 days)
    Cookies.set('token', token, { expires: 7 });
  } else {
    // Session cookie (expires on browser close)
    Cookies.set('token', token);
  }
  
  const user = jwtDecode<User>(token);
  setUser(user);
};
```

### Q87: How do we handle password reset flow?
**Answer:** Multi-step password reset:

```tsx
// Step 1: Request reset
const requestPasswordReset = async (email: string) => {
  await api.post('/auth/forgot-password', { email });
  toast.success('Reset link sent to your email');
};

// Step 2: Verify token and reset
const resetPassword = async (token: string, newPassword: string) => {
  await api.post('/auth/reset-password', { token, newPassword });
  toast.success('Password reset successful');
  navigate('/login');
};

// Component
const ResetPassword = () => {
  const [searchParams] = useSearchParams();
  const token = searchParams.get('token');
  const { register, handleSubmit } = useForm();
  
  const onSubmit = async (data: { password: string; confirmPassword: string }) => {
    if (data.password !== data.confirmPassword) {
      toast.error("Passwords don't match");
      return;
    }
    await resetPassword(token!, data.password);
  };
  
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input type="password" {...register('password')} />
      <input type="password" {...register('confirmPassword')} />
      <button type="submit">Reset Password</button>
    </form>
  );
};
```

### Q88: How do we implement OAuth/Social login?
**Answer:** OAuth integration pattern:

```tsx
const handleGoogleLogin = () => {
  // Redirect to OAuth provider
  window.location.href = `${API_URL}/auth/google`;
};

// Callback handler
const OAuthCallback = () => {
  const [searchParams] = useSearchParams();
  const token = searchParams.get('token');
  
  useEffect(() => {
    if (token) {
      Cookies.set('token', token);
      const user = jwtDecode<User>(token);
      setUser(user);
      navigate('/dashboard');
    }
  }, [token]);
  
  return <div>Logging in...</div>;
};

// Login page
<button onClick={handleGoogleLogin}>
  <GoogleIcon /> Login with Google
</button>
```

### Q89: How do we secure sensitive data in the frontend?
**Answer:** Frontend security practices:

**1. Never store sensitive data:**
```tsx
// ❌ Don't do this
localStorage.setItem('password', password);

// ✅ Only store tokens
Cookies.set('token', token, { 
  secure: true,  // HTTPS only
  httpOnly: true, // Not accessible via JS
  sameSite: 'strict' 
});
```

**2. Sanitize user input:**
```tsx
import DOMPurify from 'dompurify';

const sanitizedHTML = DOMPurify.sanitize(userInput);
```

**3. Validate on both client and server:**
```tsx
// Client validation (UX)
const schema = z.object({
  email: z.string().email(),
});

// Always validate on server too!
```

**4. Use HTTPS:**
```tsx
// Ensure API calls use HTTPS
const api = axios.create({
  baseURL: 'https://api.example.com',
});
```

### Q90: How do we implement permission-based features?
**Answer:** Granular permission system:

```tsx
interface User {
  id: number;
  role: string;
  permissions: string[];
}

const usePermission = (permission: string) => {
  const [user] = useAtom(userAtom);
  return user?.permissions.includes(permission) ?? false;
};

// Usage
const InternshipCard = ({ internship }) => {
  const canEdit = usePermission('internship.edit');
  const canDelete = usePermission('internship.delete');
  
  return (
    <div>
      <h3>{internship.title}</h3>
      {canEdit && <button>Edit</button>}
      {canDelete && <button>Delete</button>}
    </div>
  );
};

// Permission component
const Permission = ({ 
  required, 
  children 
}: { 
  required: string; 
  children: React.ReactNode;
}) => {
  const hasPermission = usePermission(required);
  return hasPermission ? <>{children}</> : null;
};

// Usage
<Permission required="internship.create">
  <button>Create Internship</button>
</Permission>
```

---

## Performance & Best Practices

### Q91: What are React performance optimization techniques we use?
**Answer:** Multiple optimization strategies:

**1. useMemo - Memoize expensive calculations:**
```tsx
const ExpensiveComponent = ({ data }) => {
  const processedData = useMemo(() => {
    return data.map(item => ({
      ...item,
      calculated: expensiveCalculation(item),
    }));
  }, [data]);
  
  return <div>{/* Use processedData */}</div>;
};
```

**2. useCallback - Memoize functions:**
```tsx
const Parent = () => {
  const [count, setCount] = useState(0);
  
  const handleClick = useCallback(() => {
    console.log('Clicked');
  }, []); // Doesn't change on re-renders
  
  return <Child onClick={handleClick} />;
};

const Child = React.memo(({ onClick }) => {
  return <button onClick={onClick}>Click</button>;
});
```

**3. React.memo - Prevent unnecessary re-renders:**
```tsx
const Card = React.memo(({ data }) => {
  return <div>{data.title}</div>;
});

// Only re-renders if data changes
```

**4. Code splitting with lazy:**
```tsx
const HeavyComponent = lazy(() => import('./HeavyComponent'));

<Suspense fallback={<Loading />}>
  <HeavyComponent />
</Suspense>
```

**5. Virtualization for long lists:**
```tsx
import { FixedSizeList } from 'react-window';

const VirtualList = ({ items }) => (
  <FixedSizeList
    height={600}
    itemCount={items.length}
    itemSize={50}
    width="100%"
  >
    {({ index, style }) => (
      <div style={style}>{items[index].name}</div>
    )}
  </FixedSizeList>
);
```

### Q92: How do we prevent memory leaks?
**Answer:** Cleanup and cancellation:

**1. Cleanup in useEffect:**
```tsx
useEffect(() => {
  const subscription = subscribeToData();
  
  return () => {
    subscription.unsubscribe();
  };
}, []);
```

**2. Cancel API requests:**
```tsx
useEffect(() => {
  const controller = new AbortController();
  
  const fetchData = async () => {
    try {
      const response = await api.get('/data', {
        signal: controller.signal,
      });
      setData(response.data);
    } catch (error) {
      if (error.name !== 'AbortError') {
        console.error(error);
      }
    }
  };
  
  fetchData();
  
  return () => controller.abort();
}, []);
```

**3. Clear timers:**
```tsx
useEffect(() => {
  const timer = setTimeout(() => {
    console.log('Delayed action');
  }, 1000);
  
  return () => clearTimeout(timer);
}, []);
```

**4. Remove event listeners:**
```tsx
useEffect(() => {
  const handleResize = () => {
    console.log('Resized');
  };
  
  window.addEventListener('resize', handleResize);
  
  return () => {
    window.removeEventListener('resize', handleResize);
  };
}, []);
```

### Q93: What are common React anti-patterns to avoid?
**Answer:** 

**1. ❌ Mutating state directly:**
```tsx
// ❌ Wrong
state.push(newItem);
setState(state);

// ✅ Correct
setState([...state, newItem]);
```

**2. ❌ Using index as key:**
```tsx
// ❌ Wrong (if list can reorder)
{items.map((item, index) => <div key={index}>{item}</div>)}

// ✅ Correct
{items.map(item => <div key={item.id}>{item}</div>)}
```

**3. ❌ Unnecessary useEffect:**
```tsx
// ❌ Wrong
const [fullName, setFullName] = useState('');
useEffect(() => {
  setFullName(`${firstName} ${lastName}`);
}, [firstName, lastName]);

// ✅ Correct
const fullName = `${firstName} ${lastName}`;
```

**4. ❌ Props drilling:**
```tsx
// ❌ Wrong
<A user={user}>
  <B user={user}>
    <C user={user}>
      <D user={user} />

// ✅ Correct (use context/Jotai)
const [user] = useAtom(userAtom);
```

**5. ❌ Inline object/array in props:**
```tsx
// ❌ Wrong (creates new object on every render)
<Component style={{ color: 'red' }} />

// ✅ Correct
const style = { color: 'red' };
<Component style={style} />
```

### Q94: How do we handle error boundaries?
**Answer:** Error boundaries catch React errors:

```tsx
class ErrorBoundary extends React.Component<
  { children: React.ReactNode },
  { hasError: boolean; error: Error | null }
> {
  constructor(props) {
    super(props);
    this.state = { hasError: false, error: null };
  }
  
  static getDerivedStateFromError(error: Error) {
    return { hasError: true, error };
  }
  
  componentDidCatch(error: Error, errorInfo: React.ErrorInfo) {
    console.error('Error caught:', error, errorInfo);
    // Log to error reporting service
  }
  
  render() {
    if (this.state.hasError) {
      return (
        <div className="error-boundary">
          <h1>Something went wrong</h1>
          <p>{this.state.error?.message}</p>
          <button onClick={() => this.setState({ hasError: false })}>
            Try again
          </button>
        </div>
      );
    }
    
    return this.props.children;
  }
}

// Usage
<ErrorBoundary>
  <App />
</ErrorBoundary>
```

### Q95: What are our code organization best practices?
**Answer:** 

**1. File structure:**
```
src/
├── components/
│   ├── ui/           # Reusable UI components
│   └── features/     # Feature-specific components
├── pages/            # Page components
├── hooks/            # Custom hooks
├── utils/            # Utility functions
├── types/            # TypeScript types
├── State/            # Jotai atoms
└── router/           # Route configuration
```

**2. Component structure:**
```tsx
// Imports
import { useState } from 'react';
import { Button } from '@/components/ui/Button';

// Types
interface Props {
  title: string;
}

// Component
export const MyComponent = ({ title }: Props) => {
  // Hooks
  const [state, setState] = useState();
  
  // Handlers
  const handleClick = () => {};
  
  // Render
  return <div>{title}</div>;
};
```

**3. Naming conventions:**
- Components: PascalCase (`StudentDashboard`)
- Files: PascalCase for components (`StudentDashboard.tsx`)
- Hooks: camelCase with 'use' prefix (`useAuth`)
- Constants: UPPER_SNAKE_CASE (`API_BASE_URL`)
- Functions: camelCase (`fetchData`)

### Q96: How do we implement SEO in a React SPA?
**Answer:** SEO strategies:

**1. React Helmet for meta tags:**
```tsx
import { Helmet } from 'react-helmet';

const InternshipPage = () => (
  <>
    <Helmet>
      <title>Internships | College ERP</title>
      <meta name="description" content="Browse available internships" />
      <meta property="og:title" content="Internships" />
    </Helmet>
    <div>{/* Page content */}</div>
  </>
);
```

**2. Semantic HTML:**
```tsx
<article>
  <header>
    <h1>Title</h1>
  </header>
  <section>
    <p>Content</p>
  </section>
</article>
```

**3. Server-side rendering (if needed):**
- Consider Next.js for better SEO

### Q97: What testing strategies do we use?
**Answer:** Testing approaches:

**1. Unit tests (Jest + React Testing Library):**
```tsx
import { render, screen, fireEvent } from '@testing-library/react';
import { Button } from './Button';

describe('Button', () => {
  it('renders with text', () => {
    render(<Button>Click me</Button>);
    expect(screen.getByText('Click me')).toBeInTheDocument();
  });
  
  it('calls onClick when clicked', () => {
    const handleClick = jest.fn();
    render(<Button onClick={handleClick}>Click</Button>);
    fireEvent.click(screen.getByText('Click'));
    expect(handleClick).toHaveBeenCalledTimes(1);
  });
});
```

**2. Integration tests:**
```tsx
it('submits form successfully', async () => {
  render(<LoginForm />);
  
  fireEvent.change(screen.getByLabelText('Email'), {
    target: { value: 'test@example.com' },
  });
  fireEvent.change(screen.getByLabelText('Password'), {
    target: { value: 'password123' },
  });
  fireEvent.click(screen.getByText('Login'));
  
  await waitFor(() => {
    expect(screen.getByText('Welcome')).toBeInTheDocument();
  });
});
```

### Q98: How do we handle browser compatibility?
**Answer:** 

**1. Babel transpilation:**
```js
// .babelrc
{
  "presets": [
    ["@babel/preset-env", {
      "targets": "> 0.25%, not dead"
    }]
  ]
}
```

**2. Polyfills:**
```tsx
import 'core-js/stable';
import 'regenerator-runtime/runtime';
```

**3. Feature detection:**
```tsx
if ('IntersectionObserver' in window) {
  // Use IntersectionObserver
} else {
  // Fallback
}
```

**4. CSS prefixes (autoprefixer):**
```css
/* Automatically adds vendor prefixes */
.box {
  display: flex;
  /* Becomes: display: -webkit-flex; display: flex; */
}
```

### Q99: What are our deployment best practices?
**Answer:** 

**1. Build optimization:**
```bash
npm run build
```

**2. Environment variables:**
```env
VITE_API_BASE_URL=https://api.production.com
```

**3. CI/CD pipeline:**
```yaml
# .github/workflows/deploy.yml
name: Deploy
on:
  push:
    branches: [main]
jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - run: npm install
      - run: npm run build
      - run: npm run deploy
```

**4. Performance monitoring:**
- Use Lighthouse
- Monitor bundle size
- Track Core Web Vitals

### Q100: What are the key takeaways from this project?
**Answer:** 

**Technical Skills:**
1. **React Ecosystem**: Hooks, Router, Context
2. **State Management**: Jotai for global state
3. **TypeScript**: Type-safe development
4. **Modern Tooling**: Vite, ESLint, Prettier
5. **UI Libraries**: Tailwind, MUI, Radix, Mantine
6. **Form Handling**: React Hook Form + Zod
7. **API Integration**: Axios with interceptors
8. **Authentication**: JWT-based auth
9. **Performance**: Code splitting, memoization
10. **Best Practices**: Clean code, accessibility

**Architecture Patterns:**
- Component composition
- Custom hooks
- Atomic design
- Role-based access control
- Error handling
- Loading states

**Development Workflow:**
- Git version control
- Code reviews
- Testing
- Documentation
- Deployment

**Soft Skills:**
- Problem-solving
- Code organization
- Team collaboration
- Best practices adherence

---

## Additional Resources

### Recommended Reading:
- React Official Documentation
- TypeScript Handbook
- Jotai Documentation
- React Router Documentation
- Tailwind CSS Documentation

### Practice Projects:
- Build a todo app with Jotai
- Create a form with React Hook Form + Zod
- Implement authentication flow
- Build a data table with sorting/filtering

### Interview Tips:
1. Explain your thought process
2. Discuss trade-offs
3. Mention alternatives
4. Talk about real project experience
5. Ask clarifying questions

---

**Good luck with your interviews! 🚀**
